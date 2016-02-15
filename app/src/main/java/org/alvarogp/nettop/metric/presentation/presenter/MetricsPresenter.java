package org.alvarogp.nettop.metric.presentation.presenter;

import org.alvarogp.nettop.common.domain.logger.Logger;
import org.alvarogp.nettop.common.domain.usecase.executable.Executable;
import org.alvarogp.nettop.common.domain.usecase.executable.execution.Execution;
import org.alvarogp.nettop.common.domain.usecase.executable.receiver.ResponseReceiver;
import org.alvarogp.nettop.common.presentation.presenter.Presenter;
import org.alvarogp.nettop.metric.domain.interactors.getmetrics.GetMetrics;
import org.alvarogp.nettop.metric.domain.interactors.getmetrics.GetMetricsRequest;
import org.alvarogp.nettop.metric.domain.interactors.getmetrics.GetMetricsRequestBuilder;
import org.alvarogp.nettop.metric.domain.model.metric.transform.filter.factory.MetricFilterType;
import org.alvarogp.nettop.metric.domain.model.metric.transform.mapper.factory.MetricMapperType;
import org.alvarogp.nettop.metric.presentation.model.MetricUiList;
import org.alvarogp.nettop.metric.presentation.model.mapper.MetricUiListMapper;
import org.alvarogp.nettop.metric.presentation.model.mapper.MetricUiMapper;
import org.alvarogp.nettop.metric.presentation.model.mapper.MetricUiMapperFactory;
import org.alvarogp.nettop.metric.presentation.parser.UpdateIntervalParser;
import org.alvarogp.nettop.common.presentation.parser.parsed.Parsed;
import org.alvarogp.nettop.metric.presentation.view.MetricsView;

import javax.inject.Inject;

public class MetricsPresenter implements Presenter<MetricsView> {
    private MetricsView metricsView; // effectively final
    private final Logger logger;
    private final GetMetrics getMetrics;
    private final MetricUiListMapper metricUiListMapper;
    private final GetMetricsRequestBuilder getMetricsRequestBuilder;
    private final MetricUiMapperFactory metricUiMapperFactory;
    private final UpdateIntervalParser updateIntervalParser;

    private Execution metricsUpdatesExecution;

    private boolean initialized = false;
    private boolean resumed = false;

    private boolean updatesEnabled = true;
    private boolean updatesExecuting = false;

    private long updateIntervalMillis;

    private MetricUiMapper metricUiMapper;

    @Inject
    public MetricsPresenter(Logger logger,
                            GetMetrics getMetrics,
                            MetricUiListMapper metricUiListMapper,
                            GetMetricsRequestBuilder getMetricsRequestBuilder,
                            MetricUiMapperFactory metricUiMapperFactory,
                            UpdateIntervalParser updateIntervalParser) {
        this.logger = logger;
        this.getMetrics = getMetrics;
        this.metricUiListMapper = metricUiListMapper;
        this.getMetricsRequestBuilder = getMetricsRequestBuilder;
        this.metricUiMapperFactory = metricUiMapperFactory;
        this.updateIntervalParser = updateIntervalParser;
    }

    // PUBLIC INTERFACE

    /**
     * Call it at startup before {@link #initialize(MetricsView)} to set the initial interval.
     * Then, call it at any time to update the interval.
     *
     * @return true if interval has been updated, false if not (ie., value was not valid).
     */
    public boolean setUpdateIntervalSeconds(String updateIntervalSeconds) {
        Parsed<Long> parsedUpdateInterval = updateIntervalParser.parse(updateIntervalSeconds);

        boolean valid = parsedUpdateInterval.isValid();
        if (!valid) {
            showError(parsedUpdateInterval.getErrorMessage());
            return false; // interval not updated
        }

        if (initialized && parsedUpdateInterval.hasWarning()) {
            showWarning(parsedUpdateInterval.getWarningMessage());
        }

        this.updateIntervalMillis = parsedUpdateInterval.getValue();
        logger.info(this, "New update interval: " + updateIntervalMillis + " ms");

        if (updatesExecuting) { // re-launch updates to use new interval
            getMetricsUpdates();
        }

        return true; // interval updated
    }

    /**
     * Call it at startup before {@link #initialize(MetricsView)} to set the initial value.
     * Then, call it at any time to toggle the filtering of inactive metrics.
     */
    public void setFilterInactiveMetrics(boolean filterInactiveMetrics) {
        MetricFilterType filterType = filterInactiveMetrics ?
                MetricFilterType.REMOVE_INACTIVE_METRICS :
                MetricFilterType.KEEP_ALL;
        getMetricsRequestBuilder.setMetricFilterType(filterType);

        requestDataUpdated();
    }

    /**
     * Call it at startup before {@link #initialize(MetricsView)} to set the initial type.
     * Then, call it at any time to change the metric mapper type.
     */
    public void setMetricMapperType(MetricMapperType type) {
        getMetricsRequestBuilder.setMetricMapperType(type);

        requestDataUpdated();
    }

    /**
     * Call it at startup before {@link #initialize(MetricsView)} to set the initial value.
     * Then, call it at any time to set whether display total or separate rx/tx values.
     */
    public void setDisplaySeparateMetricValues(boolean displaySeparateMetricValues) {
        metricUiMapper = displaySeparateMetricValues ?
                metricUiMapperFactory.createDisplayingRxTxValues() :
                metricUiMapperFactory.createDisplayingTotalValue();

        requestDataUpdated();
    }

    /**
     * Enables or disables the metrics updates.
     *
     * You can call it at startup to set the initial value. If you do not do so, the presenter
     * default value will be used.
     *
     * Call at any time to enable or disable metric updates.
     */
    public void setUpdatesEnabled(boolean enabled) {
        updatesEnabled = enabled;
        if (enabled) { // enabled updates
            if (resumed && !updatesExecuting) { // updates can run and no running
                startMetricsUpdates();
            }
        } else { // disabled updates
            if (updatesExecuting) { // updates are running
                stopMetricsUpdates();
            }
        }
    }

    /**
     * Request a metrics refresh.
     */
    public void getMetrics() {
        getMetricsOnce();
    }

    // LIFECYCLE METHODS

    /**
     * You must call the following methods before calling this one:
     * <ul>
     *     <li>{@link #setUpdateIntervalSeconds(String)}</li>
     *     <li>{@link #setFilterInactiveMetrics(boolean)}</li>
     *     <li>{@link #setMetricMapperType(MetricMapperType)}</li>
     *     <li>{@link #setDisplaySeparateMetricValues(boolean)}</li>
     * </ul>
     */
    @Override
    public void initialize(MetricsView view) {
        logger.debug(this, "initialize()");
        metricsView = view;
        initialized = true;

        getMetricsOnce();
    }

    @Override
    public void resume() {
        logger.debug(this, "resume()");
        if (updatesEnabled) {
            startMetricsUpdates();
        }
        resumed = true;
    }

    @Override
    public void pause() {
        logger.debug(this, "pause()");
        if (updatesEnabled) {
            stopMetricsUpdates();
        }
        resumed = false;
    }

    @Override
    public void destroy() {
        logger.debug(this, "destroy()");
        initialized = false;
    }

    // UTILITY METHODS

    /**
     * Call this whenever the request data has been updated (eg. a change in
     * {@link #getMetricsRequestBuilder} or {@link #metricUiMapper}).
     *
     * It ensures the view will be updated to display proper data and that updates are executing
     * with up to date request data.
     */
    private void requestDataUpdated() {
        // If updates are running, re-launch them to be executed with the new request data,
        // which will trigger an immediate GetMetrics request
        // If updates are not running, trigger a single GetMetrics request
        if (initialized) {
            if (updatesExecuting) {
                getMetricsUpdates(); // re-launch updates
            } else {
                getMetricsOnce(); // launch a single request
            }
        }
    }

    private void startMetricsUpdates() {
        getMetricsUpdates();
        updatesExecuting = true;
    }

    private void stopMetricsUpdates() {
        stop(metricsUpdatesExecution);
        updatesExecuting = false;
    }

    private void getMetricsOnce() {
        execute(getMetricsExecutable());
    }

    private void getMetricsUpdates() {
        stop(metricsUpdatesExecution);
        metricsUpdatesExecution = execute(getMetricsExecutable().executedEach(updateIntervalMillis));
    }

    private Executable<MetricUiList> getMetricsExecutable() {
        GetMetricsRequest getMetricsRequest = getMetricsRequestBuilder.createGetMetricsRequest();
        // Copy metricUiMapper to guard in case it is modified between calling this and its use
        // in MetricUiListMapper.transform() after getMetrics ends
        MetricUiMapper metricUiMapper = this.metricUiMapper;
        return getMetrics.getExecutable(getMetricsRequest)
                .map(getMetricsResponse -> metricUiListMapper.transform(getMetricsResponse.getMetricList(), metricUiMapper));
    }

    private Execution execute(Executable<MetricUiList> executable) {
        return executable.execute(new MetricsReceiver());
    }

    private void stop(Execution execution) {
        if (execution != null) {
            execution.stop();
        }
    }

    // VIEW ACTIONS

    private void showError(String message) {
        metricsView.showError(message);
    }

    private void showError(Throwable e) {
        String message = e.getMessage();
        if (message == null || message.equals("")) {
            message = e.toString();
        }
        showError(message);
    }

    private void showWarning(String message) {
        metricsView.showWarning(message);
    }

    private void showMetrics(MetricUiList metrics) {
        metricsView.renderTotalMetric(metrics.getTotalMetric());
        metricsView.renderMetrics(metrics.getMetrics());
    }

    // DOMAIN RESPONSE HANDLER

    private class MetricsReceiver extends ResponseReceiver<MetricUiList> {
        @Override
        public void onCompleted() {
            // Only getMetrics completes, do nothing
        }

        @Override
        public void onError(Throwable e) {
            showError(e);
        }

        @Override
        public void onNext(MetricUiList metrics) {
            showMetrics(metrics);
        }
    }
}

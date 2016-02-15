package org.alvarogp.nettop.metric.presentation.view.android.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Checkable;
import android.widget.EditText;

import org.alvarogp.nettop.R;
import org.alvarogp.nettop.common.presentation.view.android.activity.BaseActivityView;
import org.alvarogp.nettop.common.presentation.view.android.input.InputManager;
import org.alvarogp.nettop.metric.di.components.DaggerMetricComponent;
import org.alvarogp.nettop.metric.di.components.MetricComponent;
import org.alvarogp.nettop.metric.domain.model.metric.transform.mapper.factory.MetricMapperType;
import org.alvarogp.nettop.metric.presentation.model.MetricUi;
import org.alvarogp.nettop.metric.presentation.presenter.MetricsPresenter;
import org.alvarogp.nettop.metric.presentation.view.MetricsView;
import org.alvarogp.nettop.metric.presentation.view.android.adapter.MetricsAdapter;
import org.alvarogp.nettop.metric.presentation.view.android.renderer.MetricRenderer;
import org.alvarogp.nettop.metric.presentation.view.android.renderer.MetricRendererFactory;
import org.alvarogp.nettop.metric.presentation.view.android.state.MetricsActionsState;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MetricsActivity extends BaseActivityView implements MetricsView {
    private MetricComponent metricComponent;
    @Inject MetricsPresenter presenter;
    @Inject MetricsActionsState state;

    @Bind(R.id.metrics_actions_root) View metricsActionsRootView;

    @Bind(R.id.auto_refresh_checkbox) Checkable autoRefreshCheckable;
    @Bind(R.id.update_interval_root) View updateIntervalRootView;

    @Bind(R.id.update_interval_seconds) EditText updateIntervalSecondsView;
    @Inject InputManager inputManager;

    @Bind(R.id.filter_checkbox) Checkable filterCheckable;

    @Bind(R.id.display_separate_metric_values_checkbox) Checkable displaySeparateMetricValuesCheckable;

    @Bind(R.id.metric_mapper_type_per_second_radiobutton) Checkable perSecondMetricMapperView;
    @Bind(R.id.metric_mapper_type_cumulative_radiobutton) Checkable cumulativeMetricMapperView;
    @Bind(R.id.metric_mapper_type_raw_radiobutton) Checkable rawMetricMapperView;

    @Bind(R.id.totals_metric) View totalView;
    @Inject MetricRendererFactory metricRendererFactory;
    private MetricRenderer totalViewRenderer;

    @Bind(R.id.metrics_list) RecyclerView metricsList;
    @Inject MetricsAdapter metricsAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metrics);
        initialize();
    }

    private void initialize() {
        initializeInjector();
        restoreState();
        initializeViews();
        initializePresenter(); // call this as soon as possible to start background work
        setViewsFromState();
    }

    private void initializeInjector() {
        metricComponent = DaggerMetricComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
        metricComponent.inject(this);
    }

    /**
     * Dependencies:
     * <ul>
     *     <li>{@link #initializeInjector()} as {@link #state} is injected</li>
     * </ul>
     */
    private void restoreState() {
        state.restore();
    }

    /**
     * Dependencies:
     * <ul>
     *     <li>{@link #initializeInjector()} some used members are injected</li>
     * </ul>
     */
    private void initializeViews() {
        ButterKnife.bind(this);

        updateIntervalSecondsView.setOnEditorActionListener((v, actionId, event) -> updateIntervalUpdated(v, v.getText()));

        totalViewRenderer = metricRendererFactory.createMetricRenderer(totalView);

        metricsList.setLayoutManager(new LinearLayoutManager(this));
        metricsList.setAdapter(metricsAdapter);
    }

    /**
     * Dependencies:
     * <ul>
     *     <li>{@link #initializeViews()} as views are used</li>
     *     <li>{@link #restoreState()} to set views state</li>
     * </ul>
     */
    private void setViewsFromState() {
        autoRefreshCheckable.setChecked(state.autoRefreshStatus);
        // Note for future maintainers: visibility state of views is not restored by framework,
        // so following call should be done unconditionally
        showUpdateInterval(state.autoRefreshStatus);

        updateIntervalSecondsView.setText(state.updateIntervalSeconds);

        filterCheckable.setChecked(state.filterInactiveMetrics);

        displaySeparateMetricValuesCheckable.setChecked(state.displaySeparateMetricValues);

        setCheckedMetricMapperType(state.metricMapperType);
    }

    /**
     * Dependencies:
     * <ul>
     *     <li>{@link #initializeInjector()} as {@link #presenter} is injected</li>
     *     <li>{@link #restoreState()} to set correct state on presenter</li>
     *     <li>{@link #initializeViews()} to avoid null pointer exceptions on calls to set view content</li>
     * </ul>
     */
    private void initializePresenter() {
        setPresenterFromState(); // needs to be done before initialize()
        presenter.initialize(this);
    }

    private void setPresenterFromState() {
        presenter.setUpdatesEnabled(state.autoRefreshStatus);
        presenter.setUpdateIntervalSeconds(state.updateIntervalSeconds);
        presenter.setFilterInactiveMetrics(state.filterInactiveMetrics);
        presenter.setDisplaySeparateMetricValues(state.displaySeparateMetricValues);
        presenter.setMetricMapperType(state.metricMapperType);
    }

    @Override
    protected void onStart() {
        super.onStart();
        logger.debug(this, "onStart()");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        logger.debug(this, "onRestoreInstanceState(" + savedInstanceState + ")");
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.pause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        logger.debug(this, "onSaveInstanceState(" + outState + ")");
    }

    @Override
    protected void onStop() {
        super.onStop();
        logger.debug(this, "onStop()");
        state.save();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        logger.debug(this, "onRestart()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_settings) {
        //    return true;
        //}
        if (id == R.id.menu_item_reset_actions) {
            resetActions();
            return true; // event consumed
        }

        return super.onOptionsItemSelected(item);
    }

    public void resetActions() {
        state.reset();
        setViewsFromState();
        setPresenterFromState();
    }

    @OnClick(R.id.update_metrics_button)
    public void updateMetricsClicked() {
        presenter.getMetrics();
    }

    @OnClick(R.id.auto_refresh_checkbox)
    public void autoRefreshClicked(Checkable autoRefresh) {
        boolean checked = autoRefresh.isChecked();
        state.autoRefreshStatus = checked;
        presenter.setUpdatesEnabled(checked);
        showUpdateInterval(checked);
        inputManager.hideSoftInputFrom(updateIntervalSecondsView); // hide IME if it was visible
    }

    public boolean updateIntervalUpdated(View inputView, CharSequence updateIntervalSeconds) {
        String updateIntervalSecondsString = updateIntervalSeconds.toString();
        boolean success = presenter.setUpdateIntervalSeconds(updateIntervalSecondsString);
        if (success) {
            state.updateIntervalSeconds = updateIntervalSecondsString;
            // After a successful update interval change, we want IME to be hidden, so that metrics
            // can be viewed properly. Also, focus must be removed from input view to avoid
            // - its cursor blinking on the screen,
            // - IME appearing again after an orientation change (landscape to portrait or vice versa)
            //   or after switching to another app and then back to this.
            //
            // Returning false (ie., action not consumed here) triggers the default behaviour, which
            // for actionDone is to hide the IME. But focus remains on the input view.
            // If, before returning false we change focus to another view, IME is not hidden.
            //
            // So, in order to get the desired behaviour, we need to
            // - return true (ie., action has been consumed) so that no default behaviour is performed,
            // - manually hide the IME,
            // - set focus to another view.
            inputManager.hideSoftInputFrom(inputView);
            metricsActionsRootView.requestFocus();
        }
        // Keep IME opened if interval was not valid
        return true; // action consumed
    }

    @OnClick(R.id.filter_checkbox)
    public void filterClicked(Checkable filter) {
        boolean checked = filter.isChecked();
        state.filterInactiveMetrics = checked;
        presenter.setFilterInactiveMetrics(checked);
    }

    @OnClick(R.id.display_separate_metric_values_checkbox)
    public void displaySeparateMetricValuesClicked(Checkable displaySeparateMetricValues) {
        boolean checked = displaySeparateMetricValues.isChecked();
        state.displaySeparateMetricValues = checked;
        presenter.setDisplaySeparateMetricValues(checked);
    }

    @OnClick(R.id.metric_mapper_type_per_second_radiobutton)
    public void perSecondMetricMapperTypeChecked() {
        setMetricMapperType(MetricMapperType.PER_SECOND, R.string.metric_mapper_type_per_second_description);
    }

    @OnClick(R.id.metric_mapper_type_cumulative_radiobutton)
    public void cumulativeMetricMapperTypeChecked() {
        setMetricMapperType(MetricMapperType.CUMULATIVE, R.string.metric_mapper_type_cumulative_description);
    }

    @OnClick(R.id.metric_mapper_type_raw_radiobutton)
    public void rawMetricMapperTypeChecked() {
        setMetricMapperType(MetricMapperType.RAW, R.string.metric_mapper_type_raw_description);
    }

    private void setMetricMapperType(MetricMapperType metricMapperType, int descriptionStringId) {
        state.metricMapperType = metricMapperType;
        presenter.setMetricMapperType(metricMapperType);
        showInfo(getString(descriptionStringId));
    }

    private void showUpdateInterval(boolean showUpdateInterval) {
        int visibility;
        if (showUpdateInterval) {
            // Set current update interval seconds to view, in case it had a previously edited
            // but not submitted value
            updateIntervalSecondsView.setText(state.updateIntervalSeconds);
            visibility = View.VISIBLE;
        } else {
            visibility = View.INVISIBLE;
        }
        updateIntervalRootView.setVisibility(visibility);
    }

    private void setCheckedMetricMapperType(MetricMapperType metricMapperType) {
        switch (metricMapperType) {
            case PER_SECOND:
                perSecondMetricMapperView.setChecked(true);
                break;
            case CUMULATIVE:
                cumulativeMetricMapperView.setChecked(true);
                break;
            case RAW:
                rawMetricMapperView.setChecked(true);
                break;
        }
    }

    @Override
    public void renderTotalMetric(MetricUi totalMetric) {
        totalViewRenderer.render(totalMetric);
    }

    @Override
    public void renderMetrics(List<MetricUi> metrics) {
        logger.debug(this, "Metrics received! : " + metrics.size() + " metrics");
        metricsAdapter.setMetrics(metrics);
        logger.debug(this, "Metrics rendered");
    }
}

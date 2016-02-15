package org.alvarogp.nettop.metric.presentation.view.android.state;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

import org.alvarogp.nettop.common.domain.logger.Logger;
import org.alvarogp.nettop.metric.domain.model.metric.transform.mapper.factory.MetricMapperType;

import javax.inject.Inject;

public class MetricsActionsState {
    private static final boolean DEFAULT_AUTO_REFRESH_STATUS = true;
    private static final String DEFAULT_UPDATE_INTERVAL_SECONDS = "5.0";
    private static final boolean DEFAULT_FILTER_INACTIVE_METRICS = true;
    private static final boolean DEFAULT_DISPLAY_SEPARATE_METRIC_VALUES = false;
    private static final MetricMapperType DEFAULT_METRIC_MAPPER_TYPE = MetricMapperType.PER_SECOND;

    private static final String KEY_AUTO_REFRESH_STATUS = "AutoRefreshStatus";
    private static final String KEY_UPDATE_INTERVAL_SECONDS = "UpdateIntervalSeconds";
    private static final String KEY_FILTER_INACTIVE_METRICS = "FilterInactiveMetrics";
    private static final String KEY_DISPLAY_SEPARATE_METRIC_VALUES = "DisplaySeparateMetricValues";
    private static final String KEY_METRIC_MAPPER_TYPE = "MetricMapperType";

    private static final String SHARED_PREFERENCES_NAME = "org.alvarogp.nettop.MetricsActionsState";

    private final Logger logger;
    private final Context context;

    public boolean autoRefreshStatus = DEFAULT_AUTO_REFRESH_STATUS;
    public String updateIntervalSeconds = DEFAULT_UPDATE_INTERVAL_SECONDS;
    public boolean filterInactiveMetrics = DEFAULT_FILTER_INACTIVE_METRICS;
    public boolean displaySeparateMetricValues = DEFAULT_DISPLAY_SEPARATE_METRIC_VALUES;
    public MetricMapperType metricMapperType = DEFAULT_METRIC_MAPPER_TYPE;

    @Inject
    public MetricsActionsState(Logger logger, Context context) {
        this.logger = logger;
        this.context = context;
    }

    /**
     * Set default state.
     */
    public void reset() {
        logger.debug(this, "Reset actions state to default");

        autoRefreshStatus = DEFAULT_AUTO_REFRESH_STATUS;
        updateIntervalSeconds = DEFAULT_UPDATE_INTERVAL_SECONDS;
        filterInactiveMetrics = DEFAULT_FILTER_INACTIVE_METRICS;
        displaySeparateMetricValues = DEFAULT_DISPLAY_SEPARATE_METRIC_VALUES;
        metricMapperType = DEFAULT_METRIC_MAPPER_TYPE;
    }

    /**
     * Restore previous state saved on {@link #save()}.
     *
     * If there is no saved state, current state is kept, so that calling this method has no effect.
     * This at startup means to keep the default state.
     */
    public void restore() {
        logger.debug(this, "Restore actions state");
        SharedPreferences sharedPreferences = getSharedPreferences();

        autoRefreshStatus = sharedPreferences.getBoolean(KEY_AUTO_REFRESH_STATUS, autoRefreshStatus);
        updateIntervalSeconds = sharedPreferences.getString(KEY_UPDATE_INTERVAL_SECONDS, updateIntervalSeconds);
        filterInactiveMetrics = sharedPreferences.getBoolean(KEY_FILTER_INACTIVE_METRICS, filterInactiveMetrics);
        displaySeparateMetricValues = sharedPreferences.getBoolean(KEY_DISPLAY_SEPARATE_METRIC_VALUES, displaySeparateMetricValues);
        metricMapperType = MetricMapperType.deserialize(sharedPreferences.getString(KEY_METRIC_MAPPER_TYPE, metricMapperType.serialize()));
    }

    /**
     * Save current state to be later retrieved on {@link #restore()}.
     */
    @SuppressLint("CommitPrefEdits") // commit() on private function not detected by lint
    public void save() {
        logger.debug(this, "Save actions state");
        SharedPreferences.Editor editor = getSharedPreferences().edit();

        editor.putBoolean(KEY_AUTO_REFRESH_STATUS, autoRefreshStatus);
        editor.putString(KEY_UPDATE_INTERVAL_SECONDS, updateIntervalSeconds);
        editor.putBoolean(KEY_FILTER_INACTIVE_METRICS, filterInactiveMetrics);
        editor.putBoolean(KEY_DISPLAY_SEPARATE_METRIC_VALUES, displaySeparateMetricValues);
        editor.putString(KEY_METRIC_MAPPER_TYPE, metricMapperType.serialize());

        saveChanges(editor);
    }

    private SharedPreferences getSharedPreferences() {
        return context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    private void saveChanges(SharedPreferences.Editor editor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            editor.apply(); // added in API 9, writes asynchronously to disk
            logger.debug(this, "Changes applied (API >= 9)");
        } else {
            editor.commit(); // added in API 1, writes synchronously to disk
            logger.debug(this, "Changes committed (API < 9)");
        }
    }
}

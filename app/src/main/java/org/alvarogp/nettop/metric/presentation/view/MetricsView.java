package org.alvarogp.nettop.metric.presentation.view;

import org.alvarogp.nettop.common.presentation.view.View;
import org.alvarogp.nettop.metric.presentation.model.MetricUi;

import java.util.List;

public interface MetricsView extends View {
    void renderMetrics(List<MetricUi> metrics);
    void renderTotalMetric(MetricUi totalMetric);
}

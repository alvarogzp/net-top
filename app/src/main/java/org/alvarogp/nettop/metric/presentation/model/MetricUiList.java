package org.alvarogp.nettop.metric.presentation.model;

import java.util.List;

public class MetricUiList {
    private MetricUi totalMetric;
    private List<MetricUi> metrics;

    public MetricUiList(MetricUi totalMetric, List<MetricUi> metrics) {
        this.totalMetric = totalMetric;
        this.metrics = metrics;
    }

    public MetricUi getTotalMetric() {
        return totalMetric;
    }

    public List<MetricUi> getMetrics() {
        return metrics;
    }
}

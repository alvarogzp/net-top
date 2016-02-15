package org.alvarogp.nettop.metric.domain.model.metric.transform.filter;

import org.alvarogp.nettop.metric.domain.model.metric.Metric;

public class AllMetricsFilter implements MetricFilter {
    @Override
    public Boolean call(Metric metric) {
        return true;
    }
}

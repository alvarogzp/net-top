package org.alvarogp.nettop.metric.domain.model.metric.transform.mapper;

import org.alvarogp.nettop.metric.domain.model.metric.Metric;

public class NoneMetricMapper implements MetricMapper {
    @Override
    public Metric call(Metric metric) {
        return metric;
    }
}

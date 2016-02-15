package org.alvarogp.nettop.metric.domain.interactors.getmetrics;

import org.alvarogp.nettop.common.domain.usecase.UseCaseRequest;
import org.alvarogp.nettop.metric.domain.model.metric.transform.comparator.MetricComparator;
import org.alvarogp.nettop.metric.domain.model.metric.transform.filter.MetricFilter;
import org.alvarogp.nettop.metric.domain.model.metric.transform.mapper.MetricMapper;

public class GetMetricsRequest implements UseCaseRequest {
    private final MetricMapper metricMapper;
    private final MetricFilter metricFilter;
    private final MetricComparator metricComparator;

    public GetMetricsRequest(MetricMapper metricMapper, MetricFilter metricFilter, MetricComparator metricComparator) {
        this.metricMapper = metricMapper;
        this.metricFilter = metricFilter;
        this.metricComparator = metricComparator;
    }

    public MetricMapper getMetricMapper() {
        return metricMapper;
    }

    public MetricFilter getMetricFilter() {
        return metricFilter;
    }

    public MetricComparator getMetricComparator() {
        return metricComparator;
    }
}

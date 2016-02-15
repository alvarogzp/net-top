package org.alvarogp.nettop.metric.domain.interactors.getmetrics;

import org.alvarogp.nettop.metric.domain.model.metric.transform.comparator.MetricComparator;
import org.alvarogp.nettop.metric.domain.model.metric.transform.filter.MetricFilter;
import org.alvarogp.nettop.metric.domain.model.metric.transform.filter.factory.MetricFilterFactory;
import org.alvarogp.nettop.metric.domain.model.metric.transform.filter.factory.MetricFilterType;
import org.alvarogp.nettop.metric.domain.model.metric.transform.mapper.MetricMapper;
import org.alvarogp.nettop.metric.domain.model.metric.transform.mapper.factory.MetricMapperFactory;
import org.alvarogp.nettop.metric.domain.model.metric.transform.mapper.factory.MetricMapperType;

import javax.inject.Inject;

public class GetMetricsRequestBuilder {
    private final MetricMapperFactory metricMapperFactory;
    private final MetricFilterFactory metricFilterFactory;
    private final MetricComparator metricComparator;

    private MetricMapper metricMapper;
    private MetricFilter metricFilter;

    @Inject
    public GetMetricsRequestBuilder(MetricMapperFactory metricMapperFactory,
                                    MetricFilterFactory metricFilterFactory,
                                    MetricComparator metricComparator) {
        this.metricMapperFactory = metricMapperFactory;
        this.metricFilterFactory = metricFilterFactory;
        this.metricComparator = metricComparator;
    }

    public void setMetricMapperType(MetricMapperType type) {
        this.metricMapper = metricMapperFactory.create(type);
    }

    public void setMetricFilterType(MetricFilterType type) {
        this.metricFilter = metricFilterFactory.create(type);
    }

    public GetMetricsRequest createGetMetricsRequest() {
        return new GetMetricsRequest(metricMapper, metricFilter, metricComparator);
    }
}

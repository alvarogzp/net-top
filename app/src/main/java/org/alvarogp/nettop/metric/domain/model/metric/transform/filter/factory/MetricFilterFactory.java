package org.alvarogp.nettop.metric.domain.model.metric.transform.filter.factory;

import org.alvarogp.nettop.metric.domain.model.metric.transform.filter.AllMetricsFilter;
import org.alvarogp.nettop.metric.domain.model.metric.transform.filter.MetricFilter;
import org.alvarogp.nettop.metric.domain.model.metric.transform.filter.value.ValueMetricFilterFactory;

import javax.inject.Inject;

public class MetricFilterFactory {
    private final ValueMetricFilterFactory valueMetricFilterFactory;

    @Inject
    public MetricFilterFactory(ValueMetricFilterFactory valueMetricFilterFactory) {
        this.valueMetricFilterFactory = valueMetricFilterFactory;
    }

    public MetricFilter create(MetricFilterType type) {
        switch (type) {
            case REMOVE_INACTIVE_METRICS:
                return createARemoveInactiveMetricsFilter();
            case KEEP_ALL:
                return createAKeepAllFilter();
            default:
                throw new RuntimeException("unexpected type while creating a metric filter");
        }
    }

    public MetricFilter createARemoveInactiveMetricsFilter() {
        return valueMetricFilterFactory.createWithNonZeroPositiveRange();
    }

    public MetricFilter createAKeepAllFilter() {
        return new AllMetricsFilter();
    }
}

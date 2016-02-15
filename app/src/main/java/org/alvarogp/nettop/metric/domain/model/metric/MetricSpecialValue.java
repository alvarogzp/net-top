package org.alvarogp.nettop.metric.domain.model.metric;

import javax.inject.Inject;

public class MetricSpecialValue {
    public static final long UNSUPPORTED = -1; // comes from TrafficStats.UNSUPPORTED
    public static final long NEED_MORE_DATA = -2;

    @Inject
    public MetricSpecialValue() {}

    public boolean isSpecialValue(long value) {
        return value < 0;
    }

    public boolean hasSpecialValue(Metric metric) {
        // Based on MetricValueCalculator, if any of the metric value is special, its total value
        // will be set to that special value, so only total value check is needed
        return isSpecialValue(metric.getValue());
    }
}

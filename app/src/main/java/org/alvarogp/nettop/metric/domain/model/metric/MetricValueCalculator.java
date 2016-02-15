package org.alvarogp.nettop.metric.domain.model.metric;

import javax.inject.Inject;

public class MetricValueCalculator {
    private final MetricSpecialValue metricSpecialValue;

    @Inject
    public MetricValueCalculator(MetricSpecialValue metricSpecialValue) {
        this.metricSpecialValue = metricSpecialValue;
    }

    public long calculateValue(long rxValue, long txValue) {
        if (metricSpecialValue.isSpecialValue(rxValue)) {
            return rxValue;
        } else if (metricSpecialValue.isSpecialValue(txValue)) {
            return txValue;
        }
        return rxValue + txValue;
    }
}

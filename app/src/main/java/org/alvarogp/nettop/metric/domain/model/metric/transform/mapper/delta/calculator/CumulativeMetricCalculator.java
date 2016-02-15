package org.alvarogp.nettop.metric.domain.model.metric.transform.mapper.delta.calculator;

import android.support.annotation.NonNull;

import org.alvarogp.nettop.metric.domain.model.metric.Metric;
import org.alvarogp.nettop.metric.domain.model.metric.MetricFactory;
import org.alvarogp.nettop.metric.domain.model.metric.MetricSpecialValue;

import javax.inject.Inject;

public class CumulativeMetricCalculator implements DeltaMetricCalculator {
    private final MetricFactory metricFactory;

    @Inject
    public CumulativeMetricCalculator(MetricFactory metricFactory) {
        this.metricFactory = metricFactory;
    }

    @Override
    public Metric getDeltaMetric(@NonNull Metric last, @NonNull Metric current) {
        long cumulativeRxValue = getCumulativeValue(last.getRxValue(), current.getRxValue());
        long cumulativeTxValue = getCumulativeValue(last.getTxValue(), current.getTxValue());
        return metricFactory.from(current, cumulativeRxValue, cumulativeTxValue);
    }

    private long getCumulativeValue(long lastValue, long currentValue) {
        return currentValue - lastValue;
    }

    @Override
    public Metric getFirstMetric(@NonNull Metric first) {
        return metricFactory.from(first, MetricSpecialValue.NEED_MORE_DATA, MetricSpecialValue.NEED_MORE_DATA);
    }

    @Override
    public Metric getSpecialMetric(@NonNull Metric special) {
        return special;
    }
}

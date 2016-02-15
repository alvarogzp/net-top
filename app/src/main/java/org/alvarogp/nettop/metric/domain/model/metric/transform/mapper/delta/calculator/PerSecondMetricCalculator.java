package org.alvarogp.nettop.metric.domain.model.metric.transform.mapper.delta.calculator;

import android.support.annotation.NonNull;

import org.alvarogp.nettop.metric.domain.model.metric.Metric;
import org.alvarogp.nettop.metric.domain.model.metric.MetricFactory;
import org.alvarogp.nettop.metric.domain.model.metric.MetricSpecialValue;
import org.alvarogp.nettop.metric.domain.model.unit.MetricUnit;

import javax.inject.Inject;

public class PerSecondMetricCalculator implements DeltaMetricCalculator {
    private static final float NANOS_PER_SECOND = 1000000000.0f;

    private final MetricFactory metricFactory;

    @Inject
    public PerSecondMetricCalculator(MetricFactory metricFactory) {
        this.metricFactory = metricFactory;
    }

    @Override
    public Metric getDeltaMetric(@NonNull Metric last, @NonNull Metric current) {
        float deltaSeconds = getDeltaSeconds(last.getNanoTime(), current.getNanoTime());
        long perSecondRxValue = getPerSecondValue(last.getRxValue(), current.getRxValue(), deltaSeconds);
        long perSecondTxValue = getPerSecondValue(last.getTxValue(), current.getTxValue(), deltaSeconds);
        MetricUnit unit = getUnit(current.getUnit());

        return metricFactory.from(current, perSecondRxValue, perSecondTxValue, unit);
    }

    private float getDeltaSeconds(long lastNanos, long currentNanos) {
        return (currentNanos - lastNanos) / NANOS_PER_SECOND;
    }

    private long getPerSecondValue(long lastValue, long currentValue, float deltaSeconds) {
        return (long) ((currentValue - lastValue) / deltaSeconds);
    }

    private MetricUnit getUnit(MetricUnit current) {
        if (current.equals(MetricUnit.BYTES_UNIT)) {
            return MetricUnit.BYTES_PER_SECOND_UNIT;
        }
        // Currently no more metric units are used, so throw a warning if it happens
        throw new RuntimeException("unexpected MetricUnit!");
        //return new MetricUnit(current.getPrefix(), current.getUnit(), UnitModifier.PER_SECOND);
    }

    @Override
    public Metric getFirstMetric(@NonNull Metric first) {
        return metricFactory.from(first, MetricSpecialValue.NEED_MORE_DATA, MetricSpecialValue.NEED_MORE_DATA, getUnit(first.getUnit()));
    }

    @Override
    public Metric getSpecialMetric(@NonNull Metric special) {
        return metricFactory.from(special, getUnit(special.getUnit()));
    }
}

package org.alvarogp.nettop.metric.domain.model.metric.transform.mapper.delta.calculator;

import android.support.annotation.NonNull;

import org.alvarogp.nettop.metric.domain.model.metric.Metric;

import javax.inject.Inject;

public class RawMetricCalculator implements DeltaMetricCalculator {
    @Inject
    public RawMetricCalculator() {}

    @Override
    public Metric getDeltaMetric(@NonNull Metric last, @NonNull Metric current) {
        return current;
    }

    @Override
    public Metric getFirstMetric(@NonNull Metric first) {
        return first;
    }

    @Override
    public Metric getSpecialMetric(@NonNull Metric special) {
        return special;
    }
}

package org.alvarogp.nettop.metric.domain.model.metric.transform.mapper.delta.calculator;

import android.support.annotation.NonNull;

import org.alvarogp.nettop.metric.domain.model.metric.Metric;

public interface DeltaMetricCalculator {
    Metric getDeltaMetric(@NonNull Metric last, @NonNull Metric current);
    Metric getFirstMetric(@NonNull Metric first);
    Metric getSpecialMetric(@NonNull Metric special);
}

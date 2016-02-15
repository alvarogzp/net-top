package org.alvarogp.nettop.metric.domain.model.metric.transform.filter.value.checker.range;

import org.alvarogp.nettop.metric.domain.model.metric.transform.filter.value.checker.ValueChecker;

public class RangeValueChecker implements ValueChecker {
    private final long minValue;
    private final long maxValue;

    public RangeValueChecker(long minValue, long maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    @Override
    public boolean check(long value) {
        return value >= minValue && value <= maxValue;
    }
}

package org.alvarogp.nettop.metric.domain.model.metric.transform.filter.value.checker.range;

import javax.inject.Inject;

public class RangeValueCheckerFactory {
    private static final long MIN_VALUE = Long.MIN_VALUE;
    private static final long MAX_VALUE = Long.MAX_VALUE;

    private static final RangeValueChecker FULL_RANGE = new RangeValueChecker(MIN_VALUE, MAX_VALUE);
    private static final RangeValueChecker NON_ZERO_POSITIVE_RANGE = new RangeValueChecker(1, MAX_VALUE);

    @Inject
    public RangeValueCheckerFactory() {}

    public RangeValueChecker getFullRange() {
        return FULL_RANGE;
    }

    public RangeValueChecker getNonZeroPositiveRange() {
        return NON_ZERO_POSITIVE_RANGE;
    }
}

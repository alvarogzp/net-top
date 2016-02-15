package org.alvarogp.nettop.metric.domain.model.metric.transform.filter.value;

import org.alvarogp.nettop.metric.domain.model.metric.transform.filter.value.checker.ValueChecker;
import org.alvarogp.nettop.metric.domain.model.metric.transform.filter.value.checker.range.RangeValueCheckerFactory;
import org.alvarogp.nettop.metric.domain.model.metric.transform.filter.value.selector.TotalValueSelector;
import org.alvarogp.nettop.metric.domain.model.metric.transform.filter.value.selector.ValueSelector;

import javax.inject.Inject;

public class ValueMetricFilterFactory {
    private final ValueSelector valueSelector;
    private final RangeValueCheckerFactory rangeValueCheckerFactory;

    @Inject
    public ValueMetricFilterFactory(TotalValueSelector valueSelector, RangeValueCheckerFactory rangeValueCheckerFactory) {
        this.valueSelector = valueSelector;
        this.rangeValueCheckerFactory = rangeValueCheckerFactory;
    }

    public ValueMetricFilter createWithNonZeroPositiveRange() {
        ValueChecker valueChecker = rangeValueCheckerFactory.getNonZeroPositiveRange();
        return createWithValueChecker(valueChecker);
    }

    public ValueMetricFilter createWithFullRange() {
        ValueChecker valueChecker = rangeValueCheckerFactory.getFullRange();
        return createWithValueChecker(valueChecker);
    }

    private ValueMetricFilter createWithValueChecker(ValueChecker valueChecker) {
        return new ValueMetricFilter(valueSelector, valueChecker);
    }
}

package org.alvarogp.nettop.metric.domain.model.metric.transform.filter.value;

import org.alvarogp.nettop.metric.domain.model.metric.Metric;
import org.alvarogp.nettop.metric.domain.model.metric.transform.filter.MetricFilter;
import org.alvarogp.nettop.metric.domain.model.metric.transform.filter.value.checker.ValueChecker;
import org.alvarogp.nettop.metric.domain.model.metric.transform.filter.value.selector.ValueSelector;

public class ValueMetricFilter implements MetricFilter {
    private final ValueSelector valueSelector;
    private final ValueChecker valueChecker;

    public ValueMetricFilter(ValueSelector valueSelector, ValueChecker valueChecker) {
        this.valueSelector = valueSelector;
        this.valueChecker = valueChecker;
    }

    @Override
    public Boolean call(Metric metric) {
        long value = valueSelector.getValue(metric);
        return valueChecker.check(value);
    }
}

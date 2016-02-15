package org.alvarogp.nettop.metric.domain.model.metric.transform.comparator;

import org.alvarogp.nettop.metric.domain.model.metric.Metric;

import javax.inject.Inject;

public class ValueMetricComparator implements MetricComparator {
    @Inject
    public ValueMetricComparator() {}

    @Override
    public int compare(Metric lhs, Metric rhs) {
        long lhsValue = lhs.getValue();
        long rhsValue = rhs.getValue();
        return compare(rhsValue, lhsValue);
    }

    /**
     * Copied from {@link Long#compare(long, long)}, which was added in Java 7 (Android API 19).
     *
     * Preferred over {@link Long#compareTo(Long)} to avoid a Long object allocation.
     */
    private static int compare(long lhs, long rhs) {
        return lhs < rhs ? -1 : (lhs == rhs ? 0 : 1);
    }
}

package org.alvarogp.nettop.metric.domain.model.metric.transform.filter;

import org.alvarogp.nettop.metric.domain.model.metric.Metric;

import rx.functions.Func1;

public interface MetricFilter extends Func1<Metric, Boolean> {
    /**
     * Returns true if the metric passes the filter, false otherwise.
     */
    @Override
    Boolean call(Metric metric);
}

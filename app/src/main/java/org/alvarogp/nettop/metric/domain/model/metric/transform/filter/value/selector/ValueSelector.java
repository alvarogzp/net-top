package org.alvarogp.nettop.metric.domain.model.metric.transform.filter.value.selector;

import org.alvarogp.nettop.metric.domain.model.metric.Metric;

public interface ValueSelector {
    long getValue(Metric metric);
}

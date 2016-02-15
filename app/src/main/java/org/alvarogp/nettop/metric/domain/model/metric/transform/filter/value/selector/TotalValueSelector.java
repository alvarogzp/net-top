package org.alvarogp.nettop.metric.domain.model.metric.transform.filter.value.selector;

import org.alvarogp.nettop.metric.domain.model.metric.Metric;

import javax.inject.Inject;

public class TotalValueSelector implements ValueSelector {
    @Inject
    public TotalValueSelector() {}

    @Override
    public long getValue(Metric metric) {
        return metric.getValue();
    }
}

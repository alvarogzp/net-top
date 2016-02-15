package org.alvarogp.nettop.metric.presentation.model;

import org.alvarogp.nettop.metric.domain.model.owner.Owner;

public class MetricUi {
    private final Owner owner;
    private final MetricUiValue rxValue;
    private final MetricUiValue txValue;
    private final MetricUiValue value;

    public MetricUi(Owner owner, MetricUiValue rxValue, MetricUiValue txValue, MetricUiValue value) {
        this.owner = owner;
        this.rxValue = rxValue;
        this.txValue = txValue;
        this.value = value;
    }

    public Owner getOwner() {
        return owner;
    }

    public MetricUiValue getRxValue() {
        return rxValue;
    }

    public MetricUiValue getTxValue() {
        return txValue;
    }

    public MetricUiValue getValue() {
        return value;
    }
}

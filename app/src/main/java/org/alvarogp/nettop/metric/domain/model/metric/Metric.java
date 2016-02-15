package org.alvarogp.nettop.metric.domain.model.metric;

import org.alvarogp.nettop.metric.domain.model.owner.Owner;
import org.alvarogp.nettop.metric.domain.model.unit.MetricUnit;

public class Metric {
    private final Owner owner;
    private final long rxValue;
    private final long txValue;
    private final long value;
    private final long nanoTime;
    private final MetricUnit unit;

    public Metric(Owner owner, long rxValue, long txValue, long value, long nanoTime, MetricUnit unit) {
        this.owner = owner;
        this.rxValue = rxValue;
        this.txValue = txValue;
        this.value = value;
        this.nanoTime = nanoTime;
        this.unit = unit;
    }

    public Owner getOwner() {
        return owner;
    }

    public long getRxValue() {
        return rxValue;
    }

    public long getTxValue() {
        return txValue;
    }

    public long getValue() {
        return value;
    }

    public long getNanoTime() {
        return nanoTime;
    }

    public MetricUnit getUnit() {
        return unit;
    }
}

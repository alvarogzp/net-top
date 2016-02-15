package org.alvarogp.nettop.metric.domain.model.metric.transform.mapper.delta;

import android.support.annotation.Nullable;

import org.alvarogp.nettop.metric.domain.model.metric.Metric;
import org.alvarogp.nettop.metric.domain.model.owner.Owner;

import java.util.HashMap;
import java.util.Map;

public class LastMetrics {
    private final Map<Owner, Metric> lastMetrics = new HashMap<>();

    public LastMetrics() {}

    @Nullable
    public Metric get(Metric metric) {
        return lastMetrics.get(metric.getOwner());
    }

    public void set(Metric metric) {
        lastMetrics.put(metric.getOwner(), metric);
    }

    public void remove(Metric metric) {
        lastMetrics.remove(metric.getOwner());
    }
}

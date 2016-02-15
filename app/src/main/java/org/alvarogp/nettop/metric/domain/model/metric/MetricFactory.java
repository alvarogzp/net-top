package org.alvarogp.nettop.metric.domain.model.metric;

import org.alvarogp.nettop.metric.domain.model.owner.Owner;
import org.alvarogp.nettop.metric.domain.model.unit.MetricUnit;

import javax.inject.Inject;

public class MetricFactory {
    private final MetricValueCalculator metricValueCalculator;

    @Inject
    public MetricFactory(MetricValueCalculator metricValueCalculator) {
        this.metricValueCalculator = metricValueCalculator;
    }

    public Metric from(Owner owner, long rxValue, long txValue, long nanoTime, MetricUnit unit) {
        long value = metricValueCalculator.calculateValue(rxValue, txValue);
        return new Metric(owner, rxValue, txValue, value, nanoTime, unit);
    }

    public Metric from(Metric metric, long rxValue, long txValue, MetricUnit unit) {
        return from(metric.getOwner(), rxValue, txValue, metric.getNanoTime(), unit);
    }

    public Metric from(Metric metric, long rxValue, long txValue) {
        return from(metric, rxValue, txValue, metric.getUnit());
    }

    public Metric from(Metric metric, MetricUnit unit) {
        return from(metric, metric.getRxValue(), metric.getTxValue(), unit);
    }
}

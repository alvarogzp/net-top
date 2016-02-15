package org.alvarogp.nettop.metric.domain.model.metric.transform.mapper.delta;

import org.alvarogp.nettop.metric.domain.model.metric.Metric;
import org.alvarogp.nettop.metric.domain.model.metric.MetricSpecialValue;
import org.alvarogp.nettop.metric.domain.model.metric.transform.mapper.MetricMapper;
import org.alvarogp.nettop.metric.domain.model.metric.transform.mapper.delta.calculator.DeltaMetricCalculator;

/**
 * Immutable:
 * Instances are shared, so must remain immutable to avoid changes to affect previously queued actions.
 */
public class DeltaMetricMapper implements MetricMapper {
    private final LastMetrics lastMetrics;
    private final MetricSpecialValue metricSpecialValue;
    private final DeltaMetricCalculator deltaMetricCalculator;

    public DeltaMetricMapper(LastMetrics lastMetrics,
                             MetricSpecialValue metricSpecialValue,
                             DeltaMetricCalculator deltaMetricCalculator) {
        this.lastMetrics = lastMetrics;
        this.metricSpecialValue = metricSpecialValue;
        this.deltaMetricCalculator = deltaMetricCalculator;
    }

    @Override
    public Metric call(Metric metric) {
        if (metricSpecialValue.hasSpecialValue(metric)) {
            return specialMetric(metric);
        }
        return regularMetric(metric);
    }

    private Metric specialMetric(Metric metric) {
        // last metric is no longer valid, as it is no longer the last
        lastMetrics.remove(metric);
        return deltaMetricCalculator.getSpecialMetric(metric);
    }

    private Metric regularMetric(Metric metric) {
        Metric lastMetric = lastMetrics.get(metric);
        lastMetrics.set(metric);

        if (lastMetric == null) {
            return deltaMetricCalculator.getFirstMetric(metric);
        }
        return deltaMetricCalculator.getDeltaMetric(lastMetric, metric);
    }
}

package org.alvarogp.nettop.metric.domain.model.metric.transform.mapper.delta;

import org.alvarogp.nettop.metric.domain.model.metric.MetricSpecialValue;
import org.alvarogp.nettop.metric.domain.model.metric.transform.mapper.MetricMapper;
import org.alvarogp.nettop.metric.domain.model.metric.transform.mapper.delta.calculator.CumulativeMetricCalculator;
import org.alvarogp.nettop.metric.domain.model.metric.transform.mapper.delta.calculator.PerSecondMetricCalculator;
import org.alvarogp.nettop.metric.domain.model.metric.transform.mapper.delta.calculator.RawMetricCalculator;

import javax.inject.Inject;

public class DeltaMetricMapperFactory {
    private final MetricSpecialValue metricSpecialValue;
    private final PerSecondMetricCalculator perSecondMetricCalculator;
    private final CumulativeMetricCalculator cumulativeMetricCalculator;
    private final RawMetricCalculator rawMetricCalculator;

    private LastMetrics lastMetrics = new LastMetrics();

    @Inject
    public DeltaMetricMapperFactory(MetricSpecialValue metricSpecialValue,
                                    PerSecondMetricCalculator perSecondMetricCalculator,
                                    CumulativeMetricCalculator cumulativeMetricCalculator,
                                    RawMetricCalculator rawMetricCalculator) {
        this.metricSpecialValue = metricSpecialValue;
        this.perSecondMetricCalculator = perSecondMetricCalculator;
        this.cumulativeMetricCalculator = cumulativeMetricCalculator;
        this.rawMetricCalculator = rawMetricCalculator;
    }

    /**
     * Call this whenever you are going to create a MetricMapper that is not a DeltaMetricMapper.
     *
     * It invalidates all previous metrics stored to calculate the deltas, because the external
     * MetricMapper will not be updating them, so they will become outdated.
     */
    public void notifyCreatingExternalMetricMapper() {
        // creating a new object and not clearing the current because there may be any previous
        // DeltaMetricMapper still using it
        lastMetrics = new LastMetrics();
    }

    public MetricMapper createPerSecondMetricMapper() {
        return new DeltaMetricMapper(lastMetrics, metricSpecialValue, perSecondMetricCalculator);
    }

    public MetricMapper createCumulativeMetricMapper() {
        return new DeltaMetricMapper(lastMetrics, metricSpecialValue, cumulativeMetricCalculator);
    }

    public MetricMapper createRawMetricMapper() {
        return new DeltaMetricMapper(lastMetrics, metricSpecialValue, rawMetricCalculator);
    }
}

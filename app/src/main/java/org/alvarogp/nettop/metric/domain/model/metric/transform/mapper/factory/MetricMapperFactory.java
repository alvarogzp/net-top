package org.alvarogp.nettop.metric.domain.model.metric.transform.mapper.factory;

import org.alvarogp.nettop.metric.domain.model.metric.transform.mapper.MetricMapper;
import org.alvarogp.nettop.metric.domain.model.metric.transform.mapper.delta.DeltaMetricMapperFactory;

import javax.inject.Inject;

public class MetricMapperFactory {
    private final DeltaMetricMapperFactory deltaMetricMapperFactory;

    @Inject
    public MetricMapperFactory(DeltaMetricMapperFactory deltaMetricMapperFactory) {
        this.deltaMetricMapperFactory = deltaMetricMapperFactory;
    }

    /**
     * You can use this method or one of the individual create... methods to create a new MetricMapper
     */
    public MetricMapper create(MetricMapperType type) {
        switch (type) {
            case PER_SECOND:
                return createPerSecondMetricMapper();
            case CUMULATIVE:
                return createCumulativeMetricMapper();
            case RAW:
                return createRawMetricMapper();
            default:
                throw new RuntimeException("unexpected MetricMapper type");
        }
    }

    public MetricMapper createPerSecondMetricMapper() {
        return deltaMetricMapperFactory.createPerSecondMetricMapper();
    }

    public MetricMapper createCumulativeMetricMapper() {
        return deltaMetricMapperFactory.createCumulativeMetricMapper();
    }

    public MetricMapper createRawMetricMapper() {
        return deltaMetricMapperFactory.createRawMetricMapper();
        // Not using NoneMetricMapper because it affects subsequent DeltaMetricMapper(s) used
        // by invalidating its cache. If desired to use in the future, here is the code:
        //deltaMetricMapperFactory.notifyCreatingExternalMetricMapper(); // not a DeltaMetricMapper
        //return new NoneMetricMapper();
    }
}

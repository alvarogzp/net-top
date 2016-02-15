package org.alvarogp.nettop.metric.domain.model.metric.transform.mapper.factory;

public enum MetricMapperType {
    PER_SECOND,
    CUMULATIVE,
    RAW,
    ;

    public String serialize() {
        return name();
    }

    public static MetricMapperType deserialize(String serializedMetricMapperType) {
        return valueOf(serializedMetricMapperType);
    }
}

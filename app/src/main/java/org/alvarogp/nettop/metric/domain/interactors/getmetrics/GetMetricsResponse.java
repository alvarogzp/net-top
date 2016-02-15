package org.alvarogp.nettop.metric.domain.interactors.getmetrics;

import org.alvarogp.nettop.common.domain.usecase.UseCaseResponse;
import org.alvarogp.nettop.metric.domain.model.metric.list.MetricList;

public class GetMetricsResponse implements UseCaseResponse {
    private final MetricList metricList;

    public GetMetricsResponse(MetricList metricList) {
        this.metricList = metricList;
    }

    public MetricList getMetricList() {
        return metricList;
    }
}

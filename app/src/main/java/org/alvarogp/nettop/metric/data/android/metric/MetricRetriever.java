package org.alvarogp.nettop.metric.data.android.metric;

import org.alvarogp.nettop.metric.domain.model.metric.Metric;
import org.alvarogp.nettop.metric.domain.model.metric.MetricFactory;
import org.alvarogp.nettop.metric.domain.model.owner.Owner;
import org.alvarogp.nettop.metric.domain.model.unit.MetricUnit;

import javax.inject.Inject;

public class MetricRetriever {
    private final TrafficInfo trafficInfo;
    private final MetricFactory metricFactory;

    @Inject
    public MetricRetriever(TrafficInfo trafficInfo, MetricFactory metricFactory) {
        this.trafficInfo = trafficInfo;
        this.metricFactory = metricFactory;
    }

    public Metric getTotalMetric(Owner owner) {
        long rxValue = trafficInfo.getTotalRxValue();
        long txValue = trafficInfo.getTotalTxValue();
        return createMetric(owner, rxValue, txValue);
    }

    public Metric getMetric(Owner owner) {
        int uid = owner.getId();
        long rxValue = trafficInfo.getRxValue(uid);
        long txValue = trafficInfo.getTxValue(uid);
        return createMetric(owner, rxValue, txValue);
    }

    private Metric createMetric(Owner owner, long rxValue, long txValue) {
        long nanoTime = trafficInfo.getNanoTime();
        MetricUnit unit = trafficInfo.getUnit();
        return metricFactory.from(owner, rxValue, txValue, nanoTime, unit);
    }
}

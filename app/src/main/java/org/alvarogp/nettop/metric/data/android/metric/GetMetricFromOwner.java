package org.alvarogp.nettop.metric.data.android.metric;

import org.alvarogp.nettop.common.domain.logger.Logger;
import org.alvarogp.nettop.metric.domain.model.metric.Metric;
import org.alvarogp.nettop.metric.domain.model.metric.list.MetricList;
import org.alvarogp.nettop.metric.domain.model.owner.Owner;
import org.alvarogp.nettop.metric.domain.model.owner.list.OwnerList;

import java.util.List;

import javax.inject.Inject;

import rx.functions.Func1;

public class GetMetricFromOwner implements Func1<OwnerList, MetricList> {
    private final Logger logger;
    private final MetricRetriever metricRetriever;

    @Inject
    public GetMetricFromOwner(Logger logger, MetricRetriever metricRetriever) {
        this.logger = logger;
        this.metricRetriever = metricRetriever;
    }

    @Override
    public MetricList call(OwnerList owners) {
        MetricList metrics = new MetricList();
        logger.debug(this, "Start retrieving metrics");
        retrieveTotalMetric(metrics, owners.getTotalOwner());
        retrieveMetrics(metrics, owners.getOwners());
        logger.debug(this, "Ended retrieving metrics");
        return metrics;
    }

    private void retrieveTotalMetric(MetricList metrics, Owner totalOwner) {
        Metric totalMetric = metricRetriever.getTotalMetric(totalOwner);
        metrics.setTotalMetric(totalMetric);
    }

    private void retrieveMetrics(MetricList metrics, List<Owner> owners) {
        for (Owner owner : owners) {
            Metric metric = metricRetriever.getMetric(owner);
            metrics.addMetric(metric);
        }
    }
}

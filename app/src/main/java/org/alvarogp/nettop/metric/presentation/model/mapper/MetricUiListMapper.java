package org.alvarogp.nettop.metric.presentation.model.mapper;

import org.alvarogp.nettop.common.domain.logger.Logger;
import org.alvarogp.nettop.metric.domain.model.metric.list.MetricList;
import org.alvarogp.nettop.metric.presentation.model.MetricUiList;

import javax.inject.Inject;

public class MetricUiListMapper {
    private final Logger logger;

    @Inject
    public MetricUiListMapper(Logger logger) {
        this.logger = logger;
    }

    public MetricUiList transform(MetricList metrics, MetricUiMapper metricUiMapper) {
        logger.debug(this, "Start getting MetricsUi");
        MetricUiList metricUiList = new MetricUiList(
                metricUiMapper.transform(metrics.getTotalMetric()),
                metricUiMapper.transform(metrics.getMetrics())
        );
        logger.debug(this, "Ended getting MetricsUi");
        return metricUiList;
    }
}

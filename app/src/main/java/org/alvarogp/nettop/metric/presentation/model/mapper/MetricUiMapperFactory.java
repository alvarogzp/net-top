package org.alvarogp.nettop.metric.presentation.model.mapper;

import org.alvarogp.nettop.metric.presentation.transform.value.MetricUiValueFactory;

import javax.inject.Inject;

public class MetricUiMapperFactory {
    private final MetricUiValueFactory metricUiValueFactory;

    @Inject
    public MetricUiMapperFactory(MetricUiValueFactory metricUiValueFactory) {
        this.metricUiValueFactory = metricUiValueFactory;
    }

    public MetricUiMapper createDisplayingRxTxValues() {
        return create(true, true, false);
    }

    public MetricUiMapper createDisplayingTotalValue() {
        return create(false, false, true);
    }

    private MetricUiMapper create(boolean displayRxValue, boolean displayTxValue, boolean displayTotalValue) {
        return new MetricUiMapper(metricUiValueFactory, displayRxValue, displayTxValue, displayTotalValue);
    }
}

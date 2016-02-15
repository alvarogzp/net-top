package org.alvarogp.nettop.metric.presentation.model.mapper;

import org.alvarogp.nettop.metric.domain.model.metric.Metric;
import org.alvarogp.nettop.metric.domain.model.unit.MetricUnit;
import org.alvarogp.nettop.metric.presentation.model.MetricUi;
import org.alvarogp.nettop.metric.presentation.model.MetricUiValue;
import org.alvarogp.nettop.metric.presentation.transform.value.MetricUiValueFactory;

import java.util.ArrayList;
import java.util.List;

public class MetricUiMapper {
    private final MetricUiValueFactory metricUiValueFactory;
    private final boolean displayRxValue;
    private final boolean displayTxValue;
    private final boolean displayTotalValue;

    public MetricUiMapper(MetricUiValueFactory metricUiValueFactory,
                          boolean displayRxValue,
                          boolean displayTxValue,
                          boolean displayTotalValue) {
        this.metricUiValueFactory = metricUiValueFactory;
        this.displayRxValue = displayRxValue;
        this.displayTxValue = displayTxValue;
        this.displayTotalValue = displayTotalValue;
    }

    public MetricUi transform(Metric metric) {
        return new MetricUi(
                metric.getOwner(),
                getUiValue(displayRxValue, metric.getRxValue(), metric.getUnit()),
                getUiValue(displayTxValue, metric.getTxValue(), metric.getUnit()),
                getUiValue(displayTotalValue, metric.getValue(), metric.getUnit())
        );
    }

    private MetricUiValue getUiValue(boolean display, long value, MetricUnit unit) {
        return display ?
                metricUiValueFactory.createFrom(value, unit) :
                metricUiValueFactory.createNonDisplayable();
    }

    public List<MetricUi> transform(List<Metric> metrics) {
        List<MetricUi> metricUis = new ArrayList<>(metrics.size());
        for (Metric metric : metrics) {
            metricUis.add(transform(metric));
        }
        return metricUis;
    }
}

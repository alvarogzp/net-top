package org.alvarogp.nettop.metric.presentation.transform.value.formatter;

import org.alvarogp.nettop.metric.domain.model.unit.MetricUnit;
import org.alvarogp.nettop.metric.presentation.model.MetricUiValue;

public interface ValueFormatter {
    MetricUiValue format(long value, MetricUnit unit);
}

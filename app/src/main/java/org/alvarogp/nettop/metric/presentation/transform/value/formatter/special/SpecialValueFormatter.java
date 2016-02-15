package org.alvarogp.nettop.metric.presentation.transform.value.formatter.special;

import org.alvarogp.nettop.metric.domain.model.unit.MetricUnit;
import org.alvarogp.nettop.metric.presentation.model.MetricUiValue;
import org.alvarogp.nettop.metric.presentation.transform.stringifier.value.SpecialValueStringifier;
import org.alvarogp.nettop.metric.presentation.transform.value.formatter.ValueFormatter;

import javax.inject.Inject;

public class SpecialValueFormatter implements ValueFormatter {
    private final SpecialValueStringifier specialValueStringifier;
    private final MetricUnitStringFormatter metricUnitStringFormatter;

    @Inject
    public SpecialValueFormatter(SpecialValueStringifier specialValueStringifier, MetricUnitStringFormatter metricUnitStringFormatter) {
        this.specialValueStringifier = specialValueStringifier;
        this.metricUnitStringFormatter = metricUnitStringFormatter;
    }

    @Override
    public MetricUiValue format(long value, MetricUnit unit) {
        return new MetricUiValue(
                specialValueStringifier.stringify(value),
                metricUnitStringFormatter.format(unit, value)
        );
    }
}

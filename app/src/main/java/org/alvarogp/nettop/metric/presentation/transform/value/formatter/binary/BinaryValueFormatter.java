package org.alvarogp.nettop.metric.presentation.transform.value.formatter.binary;

import org.alvarogp.nettop.metric.domain.model.unit.MetricUnit;
import org.alvarogp.nettop.metric.domain.model.unit.UnitPrefix;
import org.alvarogp.nettop.metric.presentation.model.MetricUiValue;
import org.alvarogp.nettop.metric.presentation.transform.stringifier.unit.MetricUnitStringifier;
import org.alvarogp.nettop.metric.presentation.transform.value.formatter.ValueFormatter;

import javax.inject.Inject;

public class BinaryValueFormatter implements ValueFormatter {
    private final PrefixCalculator prefixCalculator;
    private final ValueStringFormatter valueStringFormatter;
    private final MetricUnitStringifier metricUnitStringifier;

    @Inject
    public BinaryValueFormatter(PrefixCalculator prefixCalculator, ValueStringFormatter valueStringFormatter, MetricUnitStringifier metricUnitStringifier) {
        this.prefixCalculator = prefixCalculator;
        this.valueStringFormatter = valueStringFormatter;
        this.metricUnitStringifier = metricUnitStringifier;
    }

    @Override
    public MetricUiValue format(long value, MetricUnit unit) {
        long unprefixedValue = unit.getPrefix().unprefix(value);
        UnitPrefix displayPrefix = prefixCalculator.getDisplayPrefix(unprefixedValue);
        String formattedValue = valueStringFormatter.format(displayPrefix.prefix(unprefixedValue), displayPrefix);
        String formattedUnit = metricUnitStringifier.stringify(unit, displayPrefix);
        return new MetricUiValue(formattedValue, formattedUnit);
    }
}

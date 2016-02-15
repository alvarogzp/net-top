package org.alvarogp.nettop.metric.presentation.transform.value;

import org.alvarogp.nettop.metric.domain.model.metric.MetricSpecialValue;
import org.alvarogp.nettop.metric.domain.model.unit.MetricUnit;
import org.alvarogp.nettop.metric.presentation.model.MetricUiValue;
import org.alvarogp.nettop.metric.presentation.transform.value.formatter.special.SpecialValueFormatter;
import org.alvarogp.nettop.metric.presentation.transform.value.formatter.ValueFormatter;
import org.alvarogp.nettop.metric.presentation.transform.value.formatter.binary.BinaryValueFormatter;

import javax.inject.Inject;

public class MetricUiValueFactory {
    private static final MetricUiValue NON_DISPLAYABLE_UI_VALUE = new MetricUiValue();

    private final ValueFormatter binaryValueFormatter;
    private final ValueFormatter specialValueFormatter;
    private final MetricSpecialValue metricSpecialValue;

    @Inject
    public MetricUiValueFactory(BinaryValueFormatter binaryValueFormatter, SpecialValueFormatter specialValueFormatter, MetricSpecialValue metricSpecialValue) {
        this.binaryValueFormatter = binaryValueFormatter;
        this.specialValueFormatter = specialValueFormatter;
        this.metricSpecialValue = metricSpecialValue;
    }

    public MetricUiValue createFrom(long value, MetricUnit unit) {
        if (metricSpecialValue.isSpecialValue(value)) {
            return specialValueFormatter.format(value, unit);
        } else {
            return binaryValueFormatter.format(value, unit);
        }
    }

    public MetricUiValue createNonDisplayable() {
        return NON_DISPLAYABLE_UI_VALUE;
    }
}

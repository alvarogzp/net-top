package org.alvarogp.nettop.metric.presentation.transform.value.formatter.special;

import android.content.Context;

import org.alvarogp.nettop.R;
import org.alvarogp.nettop.metric.domain.model.metric.MetricSpecialValue;
import org.alvarogp.nettop.metric.domain.model.unit.MetricUnit;
import org.alvarogp.nettop.metric.presentation.transform.stringifier.unit.MetricUnitStringifier;

import javax.inject.Inject;

public class MetricUnitStringFormatter {
    private final Context context;
    private final MetricUnitStringifier metricUnitStringifier;

    @Inject
    public MetricUnitStringFormatter(Context context, MetricUnitStringifier metricUnitStringifier) {
        this.context = context;
        this.metricUnitStringifier = metricUnitStringifier;
    }

    public String format(MetricUnit unit, long value) {
        if (value == MetricSpecialValue.NEED_MORE_DATA) {
            return metricUnitStringifier.stringify(unit);
        } else {
            return context.getString(R.string.special_value_unit_empty);
        }
    }
}

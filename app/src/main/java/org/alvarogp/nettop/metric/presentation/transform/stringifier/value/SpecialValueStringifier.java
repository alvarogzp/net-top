package org.alvarogp.nettop.metric.presentation.transform.stringifier.value;

import android.content.Context;

import org.alvarogp.nettop.R;
import org.alvarogp.nettop.metric.domain.model.metric.MetricSpecialValue;
import org.alvarogp.nettop.metric.presentation.transform.stringifier.Stringifier;

import javax.inject.Inject;

public class SpecialValueStringifier extends Stringifier<Long> {
    @Inject
    public SpecialValueStringifier(Context context) {
        super(context);
    }

    @Override
    public String stringify(Long value) {
        if (value == MetricSpecialValue.UNSUPPORTED) {
            return getString(R.string.special_value_unsupported);
        } else if (value == MetricSpecialValue.NEED_MORE_DATA) {
            return getString(R.string.special_value_need_more_data);
        } else {
            return getString(R.string.special_value_unknown, value);
        }
    }
}

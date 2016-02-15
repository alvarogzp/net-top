package org.alvarogp.nettop.metric.presentation.transform.stringifier.unit;

import android.content.Context;

import org.alvarogp.nettop.R;
import org.alvarogp.nettop.metric.domain.model.unit.UnitModifier;
import org.alvarogp.nettop.metric.presentation.transform.stringifier.Stringifier;

import javax.inject.Inject;

public class UnitModifierStringifier extends Stringifier<UnitModifier> {
    @Inject
    public UnitModifierStringifier(Context context) {
        super(context);
    }

    @Override
    public String stringify(UnitModifier unitModifier) {
        switch (unitModifier) {
            case NONE:
                return getString(R.string.unit_modifier_symbol_none);
            case PER_SECOND:
                return getString(R.string.unit_modifier_symbol_per_second);
            default:
                return getString(R.string.unit_modifier_symbol_unknown);
        }
    }
}

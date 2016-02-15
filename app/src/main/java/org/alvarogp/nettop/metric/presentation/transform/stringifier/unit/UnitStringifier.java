package org.alvarogp.nettop.metric.presentation.transform.stringifier.unit;

import android.content.Context;

import org.alvarogp.nettop.R;
import org.alvarogp.nettop.metric.domain.model.unit.Unit;
import org.alvarogp.nettop.metric.presentation.transform.stringifier.Stringifier;

import javax.inject.Inject;

public class UnitStringifier extends Stringifier<Unit> {
    @Inject
    public UnitStringifier(Context context) {
        super(context);
    }

    @Override
    public String stringify(Unit unit) {
        switch (unit) {
            case BIT:
                return getString(R.string.unit_symbol_bit);
            case BYTE:
                return getString(R.string.unit_symbol_byte);
            default:
                return getString(R.string.unit_symbol_unknown);
        }
    }
}

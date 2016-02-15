package org.alvarogp.nettop.metric.presentation.transform.stringifier.unit;

import android.content.Context;

import org.alvarogp.nettop.R;
import org.alvarogp.nettop.metric.domain.model.unit.UnitPrefix;
import org.alvarogp.nettop.metric.presentation.transform.stringifier.Stringifier;

import javax.inject.Inject;

public class UnitPrefixStringifier extends Stringifier<UnitPrefix> {
    @Inject
    public UnitPrefixStringifier(Context context) {
        super(context);
    }

    @Override
    public String stringify(UnitPrefix unitPrefix) {
        switch (unitPrefix) {
            case NONE:
                return getString(R.string.unit_prefix_symbol_none);
            case KIBI:
                return getString(R.string.unit_prefix_symbol_kibi);
            case MEBI:
                return getString(R.string.unit_prefix_symbol_mebi);
            case GIBI:
                return getString(R.string.unit_prefix_symbol_gibi);
            case TEBI:
                return getString(R.string.unit_prefix_symbol_tebi);
            case PEBI:
                return getString(R.string.unit_prefix_symbol_pebi);
            case EXBI:
                return getString(R.string.unit_prefix_symbol_exbi);
            default:
                return getString(R.string.unit_prefix_symbol_unknown);
        }
    }
}

package org.alvarogp.nettop.metric.presentation.transform.stringifier.unit;

import android.content.Context;

import org.alvarogp.nettop.metric.domain.model.unit.MetricUnit;
import org.alvarogp.nettop.metric.domain.model.unit.Unit;
import org.alvarogp.nettop.metric.domain.model.unit.UnitModifier;
import org.alvarogp.nettop.metric.domain.model.unit.UnitPrefix;
import org.alvarogp.nettop.metric.presentation.transform.stringifier.Stringifier;

import javax.inject.Inject;

public class MetricUnitStringifier extends Stringifier<MetricUnit> {
    private final UnitPrefixStringifier unitPrefixStringifier;
    private final UnitStringifier unitStringifier;
    private final UnitModifierStringifier unitModifierStringifier;

    @Inject
    public MetricUnitStringifier(Context context, UnitPrefixStringifier unitPrefixStringifier, UnitStringifier unitStringifier, UnitModifierStringifier unitModifierStringifier) {
        super(context);
        this.unitPrefixStringifier = unitPrefixStringifier;
        this.unitStringifier = unitStringifier;
        this.unitModifierStringifier = unitModifierStringifier;
    }

    @Override
    public String stringify(MetricUnit metricUnit) {
        return getPrefixString(metricUnit.getPrefix()) +
                getUnitString(metricUnit.getUnit()) +
                getModifierString(metricUnit.getModifier());
    }

    /**
     * Used by BinaryValueFormatter to avoid allocation of a new MetricUnit object.
     */
    public String stringify(MetricUnit metricUnit, UnitPrefix prefix) {
        return getPrefixString(prefix) +
                getUnitString(metricUnit.getUnit()) +
                getModifierString(metricUnit.getModifier());
    }

    private String getPrefixString(UnitPrefix prefix) {
        return unitPrefixStringifier.stringify(prefix);
    }

    private String getUnitString(Unit unit) {
        return unitStringifier.stringify(unit);
    }

    private String getModifierString(UnitModifier modifier) {
        return unitModifierStringifier.stringify(modifier);
    }
}

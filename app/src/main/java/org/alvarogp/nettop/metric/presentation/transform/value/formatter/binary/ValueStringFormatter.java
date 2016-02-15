package org.alvarogp.nettop.metric.presentation.transform.value.formatter.binary;

import org.alvarogp.nettop.metric.domain.model.unit.UnitPrefix;

import javax.inject.Inject;

public class ValueStringFormatter {
    @Inject
    public ValueStringFormatter() {}

    /**
     * Formats the value for the given prefix.
     * The value must be already prefixed.
     */
    public String format(float value, UnitPrefix prefix) {
        return String.format(getFormatString(value, prefix), value);
    }

    private String getFormatString(float value, UnitPrefix prefix) {
        return "%." + getPrecision(value, prefix) + "f";
    }

    @SuppressWarnings("unused") // used in the past, may be used in the future
    private int getPrecision(float value, UnitPrefix prefix) {
        if (prefix == UnitPrefix.NONE) {
            return 0;
        }

        return 1;
    }
}

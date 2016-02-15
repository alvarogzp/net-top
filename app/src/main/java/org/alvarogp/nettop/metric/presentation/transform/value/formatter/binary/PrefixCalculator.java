package org.alvarogp.nettop.metric.presentation.transform.value.formatter.binary;

import org.alvarogp.nettop.metric.domain.model.unit.UnitPrefix;

import javax.inject.Inject;

public class PrefixCalculator {
    @Inject
    public PrefixCalculator() {}

    /**
     * Returns the prefix to use to display the value.
     *
     * NOTE: You must pass an unprefixed value, that is, if the value you have is already prefixed
     * you must remove it before calling this method.
     */
    public UnitPrefix getDisplayPrefix(long value) {
        /*
         * Assuming the prefixed are from least to greatest, choose the greatest prefix that gives
         * a value greater than or equal to 1.
         */
        UnitPrefix displayPrefix = UnitPrefix.NONE;
        for (UnitPrefix prefix : UnitPrefix.values()) {
            if (value < prefix.getValue()) {
                break;
            }
            displayPrefix = prefix;
        }
        return displayPrefix;
    }
}

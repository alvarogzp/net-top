package org.alvarogp.nettop.metric.domain.model.unit;

import static org.alvarogp.nettop.metric.domain.model.unit.UnitPrefixConstants.PREFIX_INCREMENT;

public enum UnitPrefix {
    NONE(1),
    KIBI(NONE.value * PREFIX_INCREMENT),
    MEBI(KIBI.value * PREFIX_INCREMENT),
    GIBI(MEBI.value * PREFIX_INCREMENT),
    TEBI(GIBI.value * PREFIX_INCREMENT),
    PEBI(TEBI.value * PREFIX_INCREMENT),
    EXBI(PEBI.value * PREFIX_INCREMENT);

    private final long value;

    UnitPrefix(long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }

    public float prefix(long value) {
        return (float) value / this.value;
    }

    public long unprefix(long value) {
        return value * this.value;
    }
}

class UnitPrefixConstants {
    public static final int PREFIX_INCREMENT = 1024;
}

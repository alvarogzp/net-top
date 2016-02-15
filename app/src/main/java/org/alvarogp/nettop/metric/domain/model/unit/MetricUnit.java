package org.alvarogp.nettop.metric.domain.model.unit;

public class MetricUnit {
    public static final MetricUnit BYTES_UNIT = new MetricUnit(
            UnitPrefix.NONE,
            Unit.BYTE,
            UnitModifier.NONE
    );
    public static final MetricUnit BYTES_PER_SECOND_UNIT = new MetricUnit(
            BYTES_UNIT.getPrefix(),
            BYTES_UNIT.getUnit(),
            UnitModifier.PER_SECOND
    );

    private final UnitPrefix prefix;
    private final Unit unit;
    private final UnitModifier modifier;

    public MetricUnit(UnitPrefix prefix, Unit unit, UnitModifier modifier) {
        this.prefix = prefix;
        this.unit = unit;
        this.modifier = modifier;
    }

    public UnitPrefix getPrefix() {
        return prefix;
    }

    public Unit getUnit() {
        return unit;
    }

    public UnitModifier getModifier() {
        return modifier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MetricUnit)) return false;

        MetricUnit that = (MetricUnit) o;
        return prefix == that.prefix && unit == that.unit && modifier == that.modifier;
    }
}

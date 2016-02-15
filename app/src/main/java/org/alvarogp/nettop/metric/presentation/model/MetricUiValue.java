package org.alvarogp.nettop.metric.presentation.model;

public class MetricUiValue {
    private final boolean displayable;
    private final String value;
    private final String unit;

    /**
     * Create a non displayable MetricUiValue
     */
    public MetricUiValue() {
        this.displayable = false;
        this.value = "";
        this.unit = "";
    }

    /**
     * Create a displayable MetricUiValue with provided value and unit
     */
    public MetricUiValue(String value, String unit) {
        this.displayable = true;
        this.value = value;
        this.unit = unit;
    }

    /**
     * Return whether the value should be displayed by the view or not.
     *
     * If it returns false, you should not call {@link #getValue()} nor {@link #getUnit()} methods.
     */
    public boolean isDisplayable() {
        return displayable;
    }

    public String getValue() {
        return value;
    }

    public String getUnit() {
        return unit;
    }
}

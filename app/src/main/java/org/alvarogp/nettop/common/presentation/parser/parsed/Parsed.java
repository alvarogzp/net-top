package org.alvarogp.nettop.common.presentation.parser.parsed;

public abstract class Parsed<T> {
    private static final String NO_MESSAGE = "";

    private final boolean valid;

    public Parsed(boolean valid) {
        this.valid = valid;
    }

    public boolean isValid() {
        return valid;
    }

    public T getValue() {
        throw new RuntimeException("Trying to retrieve a non-existent parsed value");
    }

    public boolean hasWarning() {
        return !NO_MESSAGE.equals(getWarningMessage());
    }

    public String getWarningMessage() {
        return NO_MESSAGE;
    }

    public String getErrorMessage() {
        return NO_MESSAGE;
    }
}

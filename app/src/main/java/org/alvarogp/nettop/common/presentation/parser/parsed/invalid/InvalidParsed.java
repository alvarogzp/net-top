package org.alvarogp.nettop.common.presentation.parser.parsed.invalid;

import org.alvarogp.nettop.common.presentation.parser.parsed.Parsed;

public class InvalidParsed<T> extends Parsed<T> {
    private final String errorMessage;

    public InvalidParsed(String errorMessage) {
        super(false);
        this.errorMessage = errorMessage;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }
}

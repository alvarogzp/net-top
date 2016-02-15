package org.alvarogp.nettop.common.presentation.parser.parsed.valid;

import org.alvarogp.nettop.common.presentation.parser.parsed.Parsed;

public class ValidParsed<T> extends Parsed<T> {
    private final T value;

    public ValidParsed(T value) {
        super(true);
        this.value = value;
    }

    @Override
    public T getValue() {
        return value;
    }
}

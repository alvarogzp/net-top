package org.alvarogp.nettop.common.presentation.parser.parsed;

import org.alvarogp.nettop.common.presentation.parser.parsed.invalid.InvalidParsed;
import org.alvarogp.nettop.common.presentation.parser.parsed.valid.ValidParsed;
import org.alvarogp.nettop.common.presentation.parser.parsed.valid.ValidParsedWithWarning;

import javax.inject.Inject;

public class ParsedFactory {
    @Inject
    public ParsedFactory() {}

    public <T> Parsed<T> createValid(T value) {
        return new ValidParsed<>(value);
    }

    public <T> Parsed<T> createValidWithWarning(T value, String warningMessage) {
        return new ValidParsedWithWarning<>(value, warningMessage);
    }

    public <T> Parsed<T> createInvalid(String errorMessage) {
        return new InvalidParsed<>(errorMessage);
    }
}

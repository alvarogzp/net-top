package org.alvarogp.nettop.common.presentation.parser;

import org.alvarogp.nettop.common.presentation.parser.parsed.Parsed;

public interface Parser<T, R> {
    Parsed<R> parse(T source);
}

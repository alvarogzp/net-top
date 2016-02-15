package org.alvarogp.nettop.metric.di.qualifiers;

import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Qualifier
@Retention(RUNTIME)
public @interface SelectUseCase {
    UseCase value();

    enum UseCase {
        METRICS,
    }
}

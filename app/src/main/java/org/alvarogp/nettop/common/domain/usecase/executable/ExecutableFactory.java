package org.alvarogp.nettop.common.domain.usecase.executable;

import org.alvarogp.nettop.common.domain.executor.ObservableExecutor;
import org.alvarogp.nettop.common.domain.converter.ObservableConverter;

import javax.inject.Inject;

import rx.Observable;

public class ExecutableFactory {
    private final ObservableExecutor observableExecutor;
    private final ObservableConverter observableConverter;

    @Inject
    public ExecutableFactory(ObservableExecutor observableExecutor, ObservableConverter observableConverter) {
        this.observableExecutor = observableExecutor;
        this.observableConverter = observableConverter;
    }

    public <T> Executable<T> createWithObservable(Observable<T> observable) {
        return new Executable<>(observableExecutor, observableConverter, observable);
    }
}

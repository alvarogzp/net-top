package org.alvarogp.nettop.common.domain.usecase.executable;

import org.alvarogp.nettop.common.domain.executor.ObservableExecutor;
import org.alvarogp.nettop.common.domain.converter.ObservableConverter;
import org.alvarogp.nettop.common.domain.usecase.executable.execution.Execution;
import org.alvarogp.nettop.common.domain.usecase.executable.receiver.ResponseReceiver;

import rx.Observable;
import rx.Subscription;
import rx.functions.Func1;

public class Executable<T> {
    private final ObservableExecutor executor;
    private final ObservableConverter converter;

    private final Observable<T> observable;

    public Executable(ObservableExecutor executor, ObservableConverter converter, Observable<T> observable) {
        this.executor = executor;
        this.converter = converter;
        this.observable = observable;
    }

    public <R> Executable<R> map(Func1<T, R> mapFunction) {
        Observable<R> mappedObservable = converter.map(observable, mapFunction);
        return new Executable<>(executor, converter, mappedObservable);
    }

    public Executable<T> executedEach(long millis) {
        Observable<T> executedEachObservable = converter.executedEach(millis, observable);
        return new Executable<>(executor, converter, executedEachObservable);
    }

    public Execution execute(ResponseReceiver<T> responseReceiver) {
        Subscription subscription = executor.execute(observable, responseReceiver);
        return new Execution(subscription);
    }
}

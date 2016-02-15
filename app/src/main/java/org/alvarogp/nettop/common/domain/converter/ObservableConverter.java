package org.alvarogp.nettop.common.domain.converter;

import org.alvarogp.nettop.common.domain.executor.ExecutionScheduler;
import org.alvarogp.nettop.common.domain.logger.Logger;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.Observable;
import rx.Scheduler;
import rx.functions.Func1;

public class ObservableConverter {
    private final Logger logger;
    private final Scheduler executionScheduler;

    @Inject
    public ObservableConverter(Logger logger, ExecutionScheduler executionScheduler) {
        this.logger = logger;
        this.executionScheduler = executionScheduler.getScheduler();
    }

    public <T> Observable<T> executedEach(long millis, Observable<T> observable) {
        return Observable.interval(0, millis, TimeUnit.MILLISECONDS, executionScheduler)
                .doOnNext(i -> logger.debug(this, "Interval: " + i))
                .doOnUnsubscribe(() -> logger.debug(this, "Interval stopped"))
                .flatMap(i -> observable);
    }

    public <T, U> Observable<U> map(Observable<T> observable, Func1<T, U> func) {
        return observable.map(func);
    }
}

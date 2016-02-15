package org.alvarogp.nettop.common.domain.executor;

import javax.inject.Inject;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;

public class ObservableExecutor {
    private final Scheduler executionScheduler;
    private final Scheduler postExecutionScheduler;

    @Inject
    public ObservableExecutor(ExecutionScheduler executionScheduler, PostExecutionThread postExecutionThread) {
        this.executionScheduler = executionScheduler.getScheduler();
        this.postExecutionScheduler = postExecutionThread.getScheduler();
    }

    public <T> Subscription execute(Observable<T> observable, Subscriber<T> subscriber) {
        return observable
                .subscribeOn(executionScheduler)
                .observeOn(postExecutionScheduler)
                .subscribe(subscriber);
    }
}

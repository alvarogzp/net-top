package org.alvarogp.nettop.common.data.executor;

import org.alvarogp.nettop.common.domain.executor.SingleThreadExecutionScheduler;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Scheduler;
import rx.schedulers.Schedulers;

@Singleton
public class BackgroundSingleThreadExecutionScheduler implements SingleThreadExecutionScheduler {
    private final Executor EXECUTOR = Executors.newSingleThreadExecutor();
    private final Scheduler SCHEDULER = Schedulers.from(EXECUTOR);

    @Inject
    public BackgroundSingleThreadExecutionScheduler() {}

    @Override
    public Scheduler getScheduler() {
        return SCHEDULER;
    }
}

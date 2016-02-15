package org.alvarogp.nettop.common.data.executor;

import org.alvarogp.nettop.common.domain.executor.ExecutionScheduler;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Scheduler;
import rx.schedulers.Schedulers;

@Singleton
public class JobExecutionScheduler implements ExecutionScheduler {
    private final Executor EXECUTOR = Executors.newCachedThreadPool();
    private final Scheduler SCHEDULER = Schedulers.from(EXECUTOR);

    @Inject
    public JobExecutionScheduler() {}

    @Override
    public Scheduler getScheduler() {
        return SCHEDULER;
    }
}

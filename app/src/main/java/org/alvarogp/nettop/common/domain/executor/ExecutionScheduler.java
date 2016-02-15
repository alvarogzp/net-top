package org.alvarogp.nettop.common.domain.executor;

import rx.Scheduler;

public interface ExecutionScheduler {
    Scheduler getScheduler();
}

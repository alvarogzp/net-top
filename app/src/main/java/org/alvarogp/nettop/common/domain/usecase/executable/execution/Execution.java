package org.alvarogp.nettop.common.domain.usecase.executable.execution;

import rx.Subscription;

public class Execution {
    private final Subscription subscription;

    public Execution(Subscription subscription) {
        this.subscription = subscription;
    }
    
    public void stop() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}

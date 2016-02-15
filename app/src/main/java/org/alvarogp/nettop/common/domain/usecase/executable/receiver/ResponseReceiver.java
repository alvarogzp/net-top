package org.alvarogp.nettop.common.domain.usecase.executable.receiver;

import rx.Subscriber;

public abstract class ResponseReceiver<T> extends Subscriber<T> {
    @Override
    public abstract void onCompleted();

    @Override
    public abstract void onError(Throwable e);

    @Override
    public abstract void onNext(T response);
}

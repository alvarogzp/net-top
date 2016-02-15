package org.alvarogp.nettop.metric.presentation.transform.stringifier;

import android.content.Context;

public abstract class Stringifier<T> {
    private final Context context;

    public Stringifier(Context context) {
        this.context = context;
    }

    public abstract String stringify(T t);

    protected String getString(int resId) {
        return context.getString(resId);
    }

    protected String getString(int resId, Object... formatArgs) {
        return context.getString(resId, formatArgs);
    }
}

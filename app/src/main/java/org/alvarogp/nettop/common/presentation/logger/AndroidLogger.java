package org.alvarogp.nettop.common.presentation.logger;

import android.util.Log;

import org.alvarogp.nettop.common.domain.logger.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AndroidLogger implements Logger {
    private static final String THREAD_CLASS_SEPARATOR = " >> ";

    private static final boolean LOG_DEBUG_MESSAGES = true;

    @Inject
    public AndroidLogger() {}

    @Override
    public void debug(Object context, String message) {
        if (LOG_DEBUG_MESSAGES) {
            Log.d(getTag(context), message);
        }
    }

    @Override
    public void info(Object context, String message) {
        Log.i(getTag(context), message);
    }

    @Override
    public void warn(Object context, String message) {
        Log.w(getTag(context), message);
    }

    @Override
    public void error(Object context, String message) {
        Log.e(getTag(context), message);
    }

    private String getTag(Object context) {
        return currentThreadName() + THREAD_CLASS_SEPARATOR + getClassName(context);
    }

    private String currentThreadName() {
        return Thread.currentThread().getName();
    }

    private String getClassName(Object context) {
        return context.getClass().getSimpleName();
    }
}

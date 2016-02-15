package org.alvarogp.nettop.common.domain.logger;

public interface Logger {
    void debug(Object context, String message);
    void info(Object context, String message);
    void warn(Object context, String message);
    void error(Object context, String message);
}

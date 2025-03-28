package org.order.core.ports.logging;

public interface LoggerPort {

    void logInfo(String message);

    void logInfo(String message, Object... params);

    void logError(String message);

    void logError(String message, Exception e);

    void logError(String message, Object... params);

}

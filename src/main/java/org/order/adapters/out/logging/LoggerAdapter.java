package org.order.adapters.out.logging;

import lombok.AllArgsConstructor;
import org.order.core.ports.logging.LoggerPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class LoggerAdapter implements LoggerPort {

    private static final Logger logger = LoggerFactory.getLogger(LoggerAdapter.class);

    @Override
    public void logInfo(String message) {
        logger.info(message);
    }

    @Override
    public void logInfo(String message, Object... params) {
        logger.info(message, params);
    }

    @Override
    public void logError(String message) {
        logger.error(message);
    }

    @Override
    public void logError(String message, Object... params) {
        logger.error(message, params);
    }

    @Override
    public void logError(String message, Exception e) {
        logger.error(message, e);
    }
}
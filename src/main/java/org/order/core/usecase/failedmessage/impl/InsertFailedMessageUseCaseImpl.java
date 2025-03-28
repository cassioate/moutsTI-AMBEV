package org.order.core.usecase.failedmessage.impl;

import org.order.core.domain.FailedMessageDomain;
import org.order.core.ports.failedmessage.InsertFailedMessagePort;
import org.order.core.ports.logging.LoggerPort;
import org.order.core.usecase.failedmessage.InsertFailedMessageUseCase;

import java.time.LocalDateTime;

public class InsertFailedMessageUseCaseImpl implements InsertFailedMessageUseCase {

    private final LoggerPort logger;

    private final InsertFailedMessagePort insertFailedMessagePort;

    public InsertFailedMessageUseCaseImpl(LoggerPort logger, InsertFailedMessagePort insertFailedMessagePort) {
        this.logger = logger;
        this.insertFailedMessagePort = insertFailedMessagePort;
    }

    @Override
    public void execute(String messageKey, String message, Exception e) {
        FailedMessageDomain failedMessageDomain = new FailedMessageDomain(
                null,
                messageKey,
                message,
                e.getMessage(),
                0,
                LocalDateTime.now(),
                false
        );

        insertFailedMessagePort.insert(failedMessageDomain);
        logger.logInfo(String.format("[INSERT - FailedMessage] - FailedMesage de key %s inserida no banco de processamento tardio!", messageKey));
    }
}

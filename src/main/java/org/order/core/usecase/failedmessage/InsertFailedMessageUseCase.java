package org.order.core.usecase.failedmessage;

public interface InsertFailedMessageUseCase {

    void execute (String messageKey, String message, Exception e);

}

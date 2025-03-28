package org.order.adapters.out.job;

import lombok.AllArgsConstructor;
import org.order.core.usecase.failedmessage.RetryFailedMessageUseCase;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RetryFailedMessages {

    private final RetryFailedMessageUseCase retryFailedMessageUseCase;

    @Scheduled(fixedRate = 10 * 60 * 50)  // A cada 30 segundos
    public void retryFailedMessages() {
        retryFailedMessageUseCase.execute();
    }

}

package org.order.adapters.out.persistence.documents;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@Document("FailedMessageDocument")
public class FailedMessageDocument {

    @Id
    private String id;

    private String messageKey;

    private String message;

    private String error;

    private int attemptCount;

    private LocalDateTime lastAttempt;

    private boolean processedSuccessfully;

}
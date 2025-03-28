package org.order.adapters.out.persistence.mapper;

import org.order.adapters.out.persistence.documents.FailedMessageDocument;
import org.order.core.domain.FailedMessageDomain;

import java.util.List;

public class MapperDocument {

    public static List<FailedMessageDocument> failedMessageDomainToDocument(List<FailedMessageDomain> list) {
        return list.stream().map(value -> {
            return FailedMessageDocument
                    .builder()
                    .id(value.getId())
                    .messageKey(value.getMessageKey())
                    .message(value.getMessage())
                    .error(value.getError())
                    .attemptCount(value.getAttemptCount())
                    .lastAttempt(value.getLastAttempt())
                    .processedSuccessfully(value.isProcessedSuccessfully())
                    .build();
        }).toList();
    }

    public static FailedMessageDocument failedMessageDomainToDocument(FailedMessageDomain domain) {
        return FailedMessageDocument
                .builder()
                .messageKey(domain.getMessageKey())
                .message(domain.getMessage())
                .error(domain.getError())
                .attemptCount(domain.getAttemptCount())
                .lastAttempt(domain.getLastAttempt())
                .processedSuccessfully(domain.isProcessedSuccessfully())
                .build();
    }

    public static List<FailedMessageDomain> failedMessageDocumentToDomain(List<FailedMessageDocument> document) {
        return document.stream()
                .map(value -> new FailedMessageDomain(
                                value.getId(),
                                value.getMessageKey(),
                                value.getMessage(),
                                value.getError(),
                                value.getAttemptCount(),
                                value.getLastAttempt(),
                                value.isProcessedSuccessfully()
                        )
                ).toList();
    }
}

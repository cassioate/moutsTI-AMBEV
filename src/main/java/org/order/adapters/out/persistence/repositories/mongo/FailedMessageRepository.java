package org.order.adapters.out.persistence.repositories.mongo;

import org.order.adapters.out.persistence.documents.FailedMessageDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FailedMessageRepository extends MongoRepository<FailedMessageDocument, String> {
    List<FailedMessageDocument> findByProcessedSuccessfullyFalseAndAttemptCountLessThan(int attemptCount);
}

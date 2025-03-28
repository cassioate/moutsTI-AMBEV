package org.order.adapters.out.persistence.repositories.mongo.impl;

import lombok.AllArgsConstructor;
import org.order.adapters.out.persistence.documents.FailedMessageDocument;
import org.order.adapters.out.persistence.mapper.MapperDocument;
import org.order.adapters.out.persistence.repositories.mongo.FailedMessageRepository;
import org.order.core.domain.FailedMessageDomain;
import org.order.core.ports.failedmessage.FindAllFailedMessageToProcessPort;
import org.order.core.ports.failedmessage.InsertAllFailedMessagePort;
import org.order.core.ports.failedmessage.InsertFailedMessagePort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class FailedMessageMongoRepositoryImpl implements InsertAllFailedMessagePort, FindAllFailedMessageToProcessPort, InsertFailedMessagePort {

    private final FailedMessageRepository failedMessageRepository;

    @Override
    public List<FailedMessageDomain> findAllToProcess() {
        int MAX_ATTEMPT = 10;
        List<FailedMessageDocument> entityList = failedMessageRepository.findByProcessedSuccessfullyFalseAndAttemptCountLessThan(MAX_ATTEMPT);
        return MapperDocument.failedMessageDocumentToDomain(entityList);
    }

    @Override
    public void insertAll(List<FailedMessageDomain> failedMessageDomainList) {
        List<FailedMessageDocument> entities = MapperDocument.failedMessageDomainToDocument(failedMessageDomainList);
        failedMessageRepository.saveAll(entities);
    }

    @Override
    public void insert(FailedMessageDomain failedMessageDomain) {
        failedMessageRepository.save(MapperDocument.failedMessageDomainToDocument(failedMessageDomain));
    }
}

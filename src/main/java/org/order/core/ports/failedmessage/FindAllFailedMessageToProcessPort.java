package org.order.core.ports.failedmessage;

import org.order.core.domain.FailedMessageDomain;

import java.util.List;

public interface FindAllFailedMessageToProcessPort {
    List<FailedMessageDomain> findAllToProcess ();
}

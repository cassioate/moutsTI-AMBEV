package org.order.core.ports.failedmessage;

import org.order.core.domain.FailedMessageDomain;

public interface InsertFailedMessagePort {
    void insert (FailedMessageDomain failedMessageDomain);
}

package org.order.core.domain;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

public class FailedMessageDomain implements Serializable {

    @Serial
    private static final long serialVersionUID = -2675177869003105393L;

    private String id;

    private String messageKey;

    private String message;

    private String error;

    private int attemptCount;

    private LocalDateTime lastAttempt;

    private boolean processedSuccessfully;

    public FailedMessageDomain(String id, String messageKey, String message, String error, int attemptCount, LocalDateTime lastAttempt, boolean processedSuccessfully) {
        this.id = id;
        this.messageKey = messageKey;
        this.message = message;
        this.error = error;
        this.attemptCount = attemptCount;
        this.lastAttempt = lastAttempt;
        this.processedSuccessfully = processedSuccessfully;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessageKey() {
        return messageKey;
    }

    public void setMessageKey(String messageKey) {
        this.messageKey = messageKey;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getAttemptCount() {
        return attemptCount;
    }

    public void setAttemptCount(int attemptCount) {
        this.attemptCount = attemptCount;
    }

    public LocalDateTime getLastAttempt() {
        return lastAttempt;
    }

    public void setLastAttempt(LocalDateTime lastAttempt) {
        this.lastAttempt = lastAttempt;
    }

    public boolean isProcessedSuccessfully() {
        return processedSuccessfully;
    }

    public void setProcessedSuccessfully(boolean processedSuccessfully) {
        this.processedSuccessfully = processedSuccessfully;
    }
}
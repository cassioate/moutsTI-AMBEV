package org.order.adapters.exceptions;

import org.springframework.http.HttpStatus;

public class RedisUnavailableException extends RuntimeException {

    private HttpStatus httpStatus;

    public RedisUnavailableException(String message) {
        super("REDIS - Service Unavailable: " + message);
        this.httpStatus = HttpStatus.SERVICE_UNAVAILABLE;
    }

    public HttpStatus getStatusCode() {
        return httpStatus;
    }

}

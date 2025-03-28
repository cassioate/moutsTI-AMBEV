package org.order.core.domain.exceptions;

import org.springframework.http.HttpStatus;

public class OrderIdNotExistException extends RuntimeException {

    private HttpStatus httpStatus;

    public OrderIdNotExistException(String message) {
        super(message);
        this.httpStatus = HttpStatus.NOT_FOUND;
    }

    public HttpStatus getStatusCode() {
        return httpStatus;
    }

}

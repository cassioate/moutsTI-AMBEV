package org.order.core.domain.exceptions;

import org.springframework.http.HttpStatus;

public class AlreadyExistException extends RuntimeException {

    private HttpStatus httpStatus;

    public AlreadyExistException(String message) {
        super(message);
        this.httpStatus = HttpStatus.CONFLICT;
    }

    public HttpStatus getStatusCode() {
        return httpStatus;
    }

}

package org.order.adapters.in.controllers.exception;

import org.order.core.domain.exceptions.OrderIdNotExistException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(OrderIdNotExistException.class)
    public ResponseEntity<String> handleOrderIdNotExistException(OrderIdNotExistException ex) {
        return new ResponseEntity<>(ex.getMessage(), ex.getStatusCode());
    }

}
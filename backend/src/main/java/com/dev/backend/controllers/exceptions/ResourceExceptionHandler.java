package com.dev.backend.controllers.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.dev.backend.services.exceptions.DatabaseException;
import com.dev.backend.services.exceptions.InvalidQuantityException;
import com.dev.backend.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        String error = "Resource not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError sdError = new StandardError(LocalDateTime.now(), status.value(), error, e.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(status).body(sdError);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request) {
        String error = "Database Error";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError sdError = new StandardError(LocalDateTime.now(), status.value(), error, e.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(status).body(sdError);
    }

    @ExceptionHandler(InvalidQuantityException.class)
    public ResponseEntity<StandardError> invalidQuantity(InvalidQuantityException e, HttpServletRequest request) {
        String error = "Quantity invalid";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError sdError = new StandardError(LocalDateTime.now(), status.value(), error, e.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(status).body(sdError);
    }

}

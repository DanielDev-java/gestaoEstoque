package com.dev.backend.services.exceptions;

public class InvalidQuantityException extends RuntimeException {

    public InvalidQuantityException(String msg) {
        super(msg);
    }
}

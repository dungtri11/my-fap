package com.portal.fap.exception;

public class BadRequestResponseException extends RuntimeException {
    public BadRequestResponseException(String asString) {
        super(asString);
    }
}

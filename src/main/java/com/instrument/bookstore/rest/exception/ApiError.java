package com.instrument.bookstore.rest.exception;

import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

public class ApiError {

    private final String code;
    private final String message;
    private final HttpStatus status;
    private final List<String> errors;

    public ApiError(final HttpStatus status, final String message) {
        this(status, message, (String) null);
    }

    public ApiError(final HttpStatus status, final String message, final String error) {
        this(status, message, error == null ? Collections.emptyList() : Collections.singletonList(error));
    }

    public ApiError(final HttpStatus status, final String message, final List<String> errors) {
        this(null, status, message, errors);
    }

    public ApiError(final String code, final HttpStatus status, final String message, final List<String> errors) {
        this.code = code;
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public List<String> getErrors() {
        return errors;
    }
}
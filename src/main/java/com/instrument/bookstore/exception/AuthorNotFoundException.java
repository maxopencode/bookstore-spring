package com.instrument.bookstore.exception;

import com.instrument.bookstore.rest.exception.ResourceNotFoundException;

/**
 * Exception to be used when author is not found
 */
public class AuthorNotFoundException extends ResourceNotFoundException {

    public AuthorNotFoundException() {
    }

    public AuthorNotFoundException(String message) {
        super(message);
    }
}

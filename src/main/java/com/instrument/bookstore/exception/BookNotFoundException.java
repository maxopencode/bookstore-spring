package com.instrument.bookstore.exception;

import com.instrument.bookstore.rest.exception.ResourceNotFoundException;

/**
 * Exception to be used when book is not found
 */
public class BookNotFoundException extends ResourceNotFoundException {

    public BookNotFoundException() {
    }

    public BookNotFoundException(String message) {
        super(message);
    }
}

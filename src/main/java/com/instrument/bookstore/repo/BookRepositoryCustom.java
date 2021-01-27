package com.instrument.bookstore.repo;

import com.instrument.bookstore.model.Book;

/**
 * This interface is used to extend default JPA book repository with custom methods.
 */
public interface BookRepositoryCustom {

    void flush();

    Book reload(Book book);
}

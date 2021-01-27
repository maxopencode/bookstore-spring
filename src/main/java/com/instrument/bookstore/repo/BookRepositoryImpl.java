package com.instrument.bookstore.repo;

import com.instrument.bookstore.model.Book;

import javax.persistence.EntityManager;

/**
 * This class is an implementation of the custom book repository.
 */
public class BookRepositoryImpl implements BookRepositoryCustom {

    private final EntityManager entityManager;

    public BookRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void flush() {
        entityManager.flush();
    }

    @Override
    public Book reload(Book book) {
        entityManager.refresh(book);
        return book;
    }
}

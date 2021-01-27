package com.instrument.bookstore.service;

import com.instrument.bookstore.model.Book;
import com.instrument.bookstore.search.SearchCriteria;
import org.springframework.data.domain.Page;

public interface BookService extends CrudService<Book, Long> {

    Page<Book> findAll(Integer page, Integer size, String sortBy, SearchCriteria criteria);

    Book findByIsbn(String isbn);

    Book update(String isbn, Book book);

    void deleteByIsbn(String isbn);
}

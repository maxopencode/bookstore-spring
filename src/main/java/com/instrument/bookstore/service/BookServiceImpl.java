package com.instrument.bookstore.service;

import com.instrument.bookstore.model.Book;
import com.instrument.bookstore.repo.BookRepository;
import com.instrument.bookstore.search.BookSpecification;
import com.instrument.bookstore.search.SearchCriteria;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BookServiceImpl extends AbstractCrudService<Book, Long, BookRepository> implements BookService {

    public BookServiceImpl(BookRepository repository) {
        super(repository);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Book> findAll(Integer page, Integer size, String sortBy, SearchCriteria criteria) {
        Pageable paging = PageRequest.of(page, size, Sort.by(sortBy));
        return getRepository().findAll(new BookSpecification(criteria), paging);
    }

    @Transactional(readOnly = true)
    @Override
    public Book findByIsbn(String isbn) {
        return getRepository().findByIsbn(isbn).orElse(null);
    }

    protected void beforeCreate(Book book) {
        book.setId(null);
    }

    @Override
    protected Book afterCreate(Book savedBook) {
        return getRepository().reload(savedBook);
    }

    @Override
    public Book update(String isbn, Book book) {
        Book existingBook = findByIsbn(isbn);
        return performUpdate(existingBook, book);
    }

    @Override
    protected Book afterUpdate(Book updatedBook) {
        getRepository().flush();
        return getRepository().reload(updatedBook);
    }

    @Override
    public void deleteByIsbn(String isbn) {
        getRepository().deleteByIsbn(isbn);
    }

    @Override
    protected void updateState(Book existingBook, Book book) {
        if (StringUtils.isNotBlank(book.getTitle())) {
            existingBook.setTitle(book.getTitle());
        }
        if (StringUtils.isNotBlank(book.getPublisher())) {
            existingBook.setPublisher(book.getPublisher());
        }
        if (book.getPublicationYear() > 0) {
            existingBook.setPublicationYear(book.getPublicationYear());
        }
        if (StringUtils.isNotBlank(book.getDescription())) {
            existingBook.setDescription(book.getDescription());
        }
        if (book.getAuthor() != null) {
            existingBook.setAuthor(book.getAuthor());
        }
    }
}

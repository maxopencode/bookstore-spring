package com.instrument.bookstore.dto;

import com.instrument.bookstore.model.Book;

import javax.validation.constraints.NotNull;

/**
 * This class encapsulates all book information.
 */
public class BookDto extends BookDetailsDto {

    @NotNull(message = "Author is required")
    private AuthorDetailsDto author;

    public BookDto() {
    }

    public BookDto(Book book) {
        super(book);
        this.author = new AuthorDetailsDto(book.getAuthor());
    }

    public AuthorDetailsDto getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDetailsDto author) {
        this.author = author;
    }

    @Override
    public void populateDetails(Book book) {
        super.populateDetails(book);
        book.setAuthor(getAuthor().toAuthor());
    }
}

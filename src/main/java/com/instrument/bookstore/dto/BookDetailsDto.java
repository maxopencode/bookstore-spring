package com.instrument.bookstore.dto;

import com.instrument.bookstore.model.Book;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

/**
 * This class encapsulates the minimal book information that can be used in either
 * list or search operations without requiring extra requests to the database.
 */
public class BookDetailsDto {

    private Long id;

    @NotBlank(message = "ISBN is mandatory")
    @Size(min = 10, max = 13, message = "ISBN must be between 10 and 13 characters")
    private String isbn;

    @NotBlank(message = "Title is mandatory")
    private String title;

    @NotBlank(message = "Publisher is mandatory")
    private String publisher;

    @PositiveOrZero
    private int publicationYear;

    private String description;

    public BookDetailsDto() {
    }

    public BookDetailsDto(Book book) {
        this.id = book.getId();
        this.isbn = book.getIsbn();
        this.title = book.getTitle();
        this.publisher = book.getPublisher();
        this.publicationYear = book.getPublicationYear();
        this.description = book.getDescription();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Book toBook() {
        Book book = new Book();
        populateDetails(book);
        return book;
    }

    public void populateDetails(Book book) {
        book.setId(getId());
        book.setIsbn(getIsbn());
        book.setTitle(getTitle());
        book.setPublisher(getPublisher());
        book.setPublicationYear(getPublicationYear());
        book.setDescription(getDescription());
    }
}

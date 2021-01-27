package com.instrument.bookstore.dto;

import com.instrument.bookstore.model.Author;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This class encapsulates all author information.
 */
public class AuthorDto extends AuthorDetailsDto {

    private Set<BookDetailsDto> books = new HashSet<>();

    public AuthorDto() {
    }

    public AuthorDto(Author author) {
        super(author);
        this.books = author.getBooks().stream().map(BookDetailsDto::new).collect(Collectors.toSet());
    }

    public Set<BookDetailsDto> getBooks() {
        return books;
    }

    public void setBooks(Set<BookDetailsDto> books) {
        this.books = books;
    }

    @Override
    public void populateDetails(Author author) {
        super.populateDetails(author);
        if (!CollectionUtils.isEmpty(getBooks())) {
            author.setBooks(getBooks().stream().map(BookDetailsDto::toBook).collect(Collectors.toSet()));
        }
    }
}

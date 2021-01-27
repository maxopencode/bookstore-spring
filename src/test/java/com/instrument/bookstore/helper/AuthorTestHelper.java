package com.instrument.bookstore.helper;

import com.instrument.bookstore.dto.AuthorDto;
import com.instrument.bookstore.model.Author;

import java.time.LocalDate;

public final class AuthorTestHelper {

    private AuthorTestHelper() {
    }

    public static Author newAuthor(Long id, String name, LocalDate birthdate) {
        Author author = new Author();
        author.setId(id);
        author.setName(name);
        author.setBirthdate(birthdate);
        return author;
    }

    public static AuthorDto newAuthorDto(Long id, String name, LocalDate birthdate) {
        AuthorDto authorDto = new AuthorDto();
        authorDto.setId(id);
        authorDto.setName(name);
        authorDto.setBirthdate(birthdate);
        return authorDto;
    }
}

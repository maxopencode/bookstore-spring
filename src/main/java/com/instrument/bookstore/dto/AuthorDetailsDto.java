package com.instrument.bookstore.dto;

import com.instrument.bookstore.model.Author;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

/**
 * This class encapsulates the minimal author information that can be used in either
 * list or search operations without requiring extra requests to the database.
 */
public class AuthorDetailsDto {

    private Long id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotNull(message = "Birthdate is required")
    @Past(message = "Invalid birthdate")
    private LocalDate birthdate;

    public AuthorDetailsDto() {
    }

    public AuthorDetailsDto(Author author) {
        this.id = author.getId();
        this.name = author.getName();
        this.birthdate = author.getBirthdate();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public Author toAuthor() {
        Author author = new Author();
        populateDetails(author);
        return author;
    }

    public void populateDetails(Author author) {
        author.setId(getId());
        author.setName(getName());
        author.setBirthdate(getBirthdate());
    }
}

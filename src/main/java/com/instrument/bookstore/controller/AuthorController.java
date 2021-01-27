package com.instrument.bookstore.controller;

import com.instrument.bookstore.dto.AuthorDetailsDto;
import com.instrument.bookstore.dto.AuthorDto;
import com.instrument.bookstore.exception.AuthorNotFoundException;
import com.instrument.bookstore.model.Author;
import com.instrument.bookstore.rest.versioning.ApiVersion;
import com.instrument.bookstore.service.AuthorService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/authors")
@ApiVersion("1")
public class AuthorController {

    private final AuthorService service;

    public AuthorController(AuthorService service) {
        this.service = service;
    }

    @ApiOperation(
            value = "Returns all authors",
            authorizations = {
                    @Authorization(value = "oauth2")
            }
    )
    @GetMapping()
    public List<AuthorDetailsDto> getAll() {
        return service.findAll().stream().map(AuthorDetailsDto::new).collect(Collectors.toList());
    }

    @ApiOperation(
            value = "Returns paginated and sorted authors",
            authorizations = {
                    @Authorization(value = "oauth2")
            }
    )
    @GetMapping(path = "/paginate")
    public Page<AuthorDetailsDto> getPage(@RequestParam(defaultValue = "0") Integer page,
                                          @RequestParam(defaultValue = "5") Integer size,
                                          @RequestParam(defaultValue = "id") String sortBy) {
        return service.findAll(page, size, sortBy).map(AuthorDetailsDto::new);
    }

    @ApiOperation(
            value = "Finds an author by id",
            authorizations = {
                    @Authorization(value = "oauth2")
            }
    )
    @GetMapping("/{id}")
    public AuthorDto findById(@PathVariable Long id) {
        Author author = service.findById(id);
        if (author == null) {
            throw new AuthorNotFoundException(String.format("No author found for id: %d", id));
        }
        return new AuthorDto(author);
    }

    @ApiOperation(
            value = "Creates a new author",
            authorizations = {
                    @Authorization(value = "oauth2")
            }
    )
    @PostMapping
    public AuthorDto create(@RequestBody @Validated AuthorDto newAuthorDto) {
        Author savedAuthor = service.create(newAuthorDto.toAuthor());
        return new AuthorDto(savedAuthor);
    }

    @ApiOperation(
            value = "Updates an existing author",
            authorizations = {
                    @Authorization(value = "oauth2")
            }
    )
    @PutMapping("/{id}")
    public AuthorDto update(@PathVariable Long id,
                            @RequestBody @Validated AuthorDto authorDto) {
        Author author = authorDto.toAuthor();
        Author updatedAuthor = service.update(id, author);
        if (updatedAuthor == null) {
            throw new AuthorNotFoundException(String.format("No author found for id: %d", id));
        }
        return new AuthorDto(updatedAuthor);
    }

    @ApiOperation(
            value = "Deletes an author using id",
            authorizations = {
                    @Authorization(value = "oauth2")
            }
    )
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }
}

package com.instrument.bookstore.controller;

import com.instrument.bookstore.dto.BookDto;
import com.instrument.bookstore.exception.BookNotFoundException;
import com.instrument.bookstore.model.Book;
import com.instrument.bookstore.rest.versioning.ApiVersion;
import com.instrument.bookstore.search.SearchCriteria;
import com.instrument.bookstore.service.BookService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/books")
@ApiVersion("1")
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @ApiOperation(
            value = "Returns paginated and sorted books",
            authorizations = {
                    @Authorization(value = "oauth2")
            }
    )
    @GetMapping(path = "/paginate")
    public Page<BookDto> getPage(@RequestParam(defaultValue = "0") Integer page,
                                 @RequestParam(defaultValue = "5") Integer size,
                                 @RequestParam(defaultValue = "isbn") String sortBy,
                                 @RequestParam(required = false) String filterBy) {

        SearchCriteria search = SearchCriteria.parse(filterBy);
        if (search == null || search.isEmpty()) {
            return service.findAll(page, size, sortBy).map(BookDto::new);
        } else {
            return service.findAll(page, size, sortBy, search).map(BookDto::new);
        }
    }

    @ApiOperation(
            value = "Finds a book by isbn",
            authorizations = {
                    @Authorization(value = "oauth2")
            }
    )
    @GetMapping("/{isbn}")
    public BookDto findByIsbn(@PathVariable String isbn) {
        Book book = service.findByIsbn(isbn);
        if (book == null) {
            throw new BookNotFoundException(String.format("No book found for isbn: %s", isbn));
        }
        return new BookDto(book);
    }

    @ApiOperation(
            value = "Creates a new book",
            authorizations = {
                    @Authorization(value = "oauth2")
            }
    )
    @PostMapping
    public BookDto create(@RequestBody @Validated BookDto newBookDto) {
        Book savedBook = service.create(newBookDto.toBook());
        return new BookDto(savedBook);
    }

    @ApiOperation(
            value = "Updates an existing book",
            authorizations = {
                    @Authorization(value = "oauth2")
            }
    )
    @PutMapping("/{isbn}")
    public BookDto update(@PathVariable String isbn,
                          @RequestBody @Validated BookDto bookDto) {
        Book book = bookDto.toBook();
        Book updatedBook = service.update(isbn, book);
        if (updatedBook == null) {
            throw new BookNotFoundException(String.format("No book found for isbn: %s", isbn));
        }
        return new BookDto(updatedBook);
    }

    @ApiOperation(
            value = "Deletes a book using isbn",
            authorizations = {
                    @Authorization(value = "oauth2")
            }
    )
    @DeleteMapping("/{isbn}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String isbn) {
        service.deleteByIsbn(isbn);
    }
}

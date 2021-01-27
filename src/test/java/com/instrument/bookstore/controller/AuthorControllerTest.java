package com.instrument.bookstore.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.instrument.bookstore.dto.AuthorDto;
import com.instrument.bookstore.model.Author;
import com.instrument.bookstore.rest.exception.ApiError;
import com.instrument.bookstore.rest.validation.ErrorResult;
import com.instrument.bookstore.rest.validation.FieldErrorResource;
import com.instrument.bookstore.service.AuthorService;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.util.List;

import static com.instrument.bookstore.helper.AuthorTestHelper.newAuthor;
import static com.instrument.bookstore.helper.AuthorTestHelper.newAuthorDto;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(controllers = AuthorController.class)
class AuthorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthorService service;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void beforeEach() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModules(new JavaTimeModule());
    }

    @Test
    public void testFindAllShouldReturnJson() throws Exception {
        LocalDate birthdate1 = LocalDate.of(1799, 5, 26);
        Author author1 = newAuthor(1L, "Aleksandr Pushkin", birthdate1);

        LocalDate birthdate2 = LocalDate.of(1814, 10, 15);
        Author author2 = newAuthor(2L, "Mikhail Lermontov", birthdate2);

        List<Author> authors = Lists.newArrayList(author1, author2);

        when(service.findAll()).thenReturn(authors);

        this.mockMvc.perform(get("/v1/authors"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Aleksandr Pushkin")))
                .andExpect(jsonPath("$[0].birthdate", is(birthdate1.toString())))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("Mikhail Lermontov")))
                .andExpect(jsonPath("$[1].birthdate", is(birthdate2.toString())));
    }

    @Test
    public void testFindByIdShouldReturnJson() throws Exception {
        LocalDate birthdate = LocalDate.of(1799, 5, 26);
        Author author = newAuthor(1L, "Aleksandr Pushkin", birthdate);

        when(service.findById(1L)).thenReturn(author);

        this.mockMvc.perform(get("/v1/authors/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Aleksandr Pushkin")))
                .andExpect(jsonPath("$.birthdate", is(birthdate.toString())));
    }

    @Test
    public void testFindByIdShouldReturnNotFound() throws Exception {
        when(service.findById(1L)).thenReturn(null);

        MvcResult mvcResult = this.mockMvc.perform(get("/v1/authors/1"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andReturn();

        String actualResponseBody = mvcResult.getResponse().getContentAsString();

        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "No author found for id: 1");
        String expectedResponseBody = objectMapper.writeValueAsString(apiError);

        assertThat(actualResponseBody).isEqualToIgnoringWhitespace(expectedResponseBody);
    }

    @Test
    public void testCreateShouldReturnJson() throws Exception {
        LocalDate birthdate = LocalDate.of(1799, 5, 26);
        AuthorDto authorDto = newAuthorDto(null, "Aleksandr Pushkin", birthdate);
        Author author = newAuthor(1L, "Aleksandr Pushkin", birthdate);

        when(service.create(any())).thenReturn(author);

        this.mockMvc.perform(
                post("/v1/authors")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(authorDto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Aleksandr Pushkin")))
                .andExpect(jsonPath("$.birthdate", is(birthdate.toString())));
    }

    @Test
    public void testCreateShouldReturnValidationErrors() throws Exception {
        AuthorDto authorDto = newAuthorDto(null, "", null);

        verifyNoInteractions(service);

        MvcResult mvcResult = this.mockMvc.perform(
                post("/v1/authors")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(authorDto)))
                .andDo(print())
                .andExpect(status().isBadRequest()).andReturn();

        String actualResponseBody = mvcResult.getResponse().getContentAsString();
        ErrorResult error = objectMapper.readValue(actualResponseBody, ErrorResult.class);

        assertTrue(StringUtils.isNotBlank(error.getMessage()), "Error message shouldn't be blank");
        assertEquals(error.getFieldErrors().size(), 2, "Invalid number of field errors");

        FieldErrorResource nameError = error.findFieldErrorByField("name");
        assertNotNull(nameError, "Name field error shouldn't be null");
        assertEquals(nameError.getResource(), "authorDto");
        assertEquals(nameError.getCode(), "NotBlank");
        assertEquals(nameError.getMessage(), "Name is mandatory");

        FieldErrorResource birthdateError = error.findFieldErrorByField("birthdate");
        assertNotNull(birthdateError, "Birthdate field error shouldn't be null");
        assertEquals(birthdateError.getResource(), "authorDto");
        assertEquals(birthdateError.getCode(), "NotNull");
        assertEquals(birthdateError.getMessage(), "Birthdate is required");
    }

}
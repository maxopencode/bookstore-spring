package com.instrument.bookstore.rest.validation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorResult {

    private String code;
    private String message;
    private List<ErrorResource> errors;
    private List<FieldErrorResource> fieldErrors;

    public ErrorResult() {
    }

    public ErrorResult(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ErrorResource> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorResource> errors) {
        this.errors = errors;
    }

    public List<FieldErrorResource> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(List<FieldErrorResource> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }

    @JsonIgnore
    public FieldErrorResource findFieldErrorByField(String field) {
        return getFieldErrors().stream()
                .filter(fieldError -> field.equals(fieldError.getField()))
                .findAny()
                .orElse(null);
    }
}
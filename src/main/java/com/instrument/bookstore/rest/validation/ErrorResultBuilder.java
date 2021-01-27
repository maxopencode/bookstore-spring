package com.instrument.bookstore.rest.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

public class ErrorResultBuilder {

    private String code;
    private String message;
    private List<ErrorResource> errorResources = new ArrayList<>();
    private List<FieldErrorResource> fieldErrorResources = new ArrayList<>();

    public ErrorResultBuilder withCode(String code) {
        this.code = code;
        return this;
    }

    public ErrorResultBuilder withMessage(String message) {
        this.message = message;
        return this;
    }

    public ErrorResultBuilder withCodeAndMessage(String code, String message) {
        withCode(code);
        withMessage(message);
        return this;
    }

    public ErrorResultBuilder withError(String resource, String code, String message) {
        withError(new ErrorResource(resource, code, message));
        return this;
    }

    public ErrorResultBuilder withError(ErrorResource errorResource) {
        this.errorResources.add(errorResource);
        return this;
    }

    public ErrorResultBuilder withFieldError(String resource, String code, String message, String field) {
        withFieldError(new FieldErrorResource(resource, code, message, field));
        return this;
    }

    public ErrorResultBuilder withFieldError(FieldErrorResource fieldErrorResource) {
        this.fieldErrorResources.add(fieldErrorResource);
        return this;
    }

    public ErrorResultBuilder withErrors(Errors errors) {
        if (errors != null) {
            for (ObjectError error : errors.getGlobalErrors()) {
                withError(error.getObjectName(), error.getCode(), error.getDefaultMessage());
            }

            for (FieldError fieldError : errors.getFieldErrors()) {
                withFieldError(fieldError.getObjectName(), fieldError.getCode(), fieldError.getDefaultMessage(), fieldError.getField());
            }
        }

        return this;
    }

    public ErrorResultBuilder withCodeMessageAndErrors(String code, String message, Errors errors) {
        withCodeAndMessage(code, message);
        withErrors(errors);

        return this;
    }

    public ErrorResult build() {
        ErrorResult errorResult = new ErrorResult(code, message);
        errorResult.setErrors(errorResources);
        errorResult.setFieldErrors(fieldErrorResources);

        return errorResult;
    }
}

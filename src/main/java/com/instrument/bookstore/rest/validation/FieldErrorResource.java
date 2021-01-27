package com.instrument.bookstore.rest.validation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FieldErrorResource extends ErrorResource {

    private String field;

    public FieldErrorResource() {
    }

    public FieldErrorResource(String resource, String code, String message, String field) {
        super(resource, code, message);
        this.field = field;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}

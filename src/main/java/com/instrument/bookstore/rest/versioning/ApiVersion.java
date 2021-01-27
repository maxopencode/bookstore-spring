package com.instrument.bookstore.rest.versioning;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation is used to define the API version(s) applicable to a RESTful controller(s). When applied on a method
 * level, it influences the API versions applicable for a single REST controller. When applied on a class level, it
 * influences the API versions of all REST controllers defined within that class. Versions are stored as {@link Double}s
 * to allow for major/minor versioning (e.g. '1.5').
 *
 * @author Ryan Morrison
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiVersion {

    String[] value();
}

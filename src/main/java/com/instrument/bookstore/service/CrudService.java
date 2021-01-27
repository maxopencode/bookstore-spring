package com.instrument.bookstore.service;

import org.springframework.data.domain.Page;

import java.util.List;

public interface CrudService<T, ID> {

    List<T> findAll();

    Page<T> findAll(Integer page, Integer size, String sortBy);

    T findById(ID id);

    T create(T entity);

    T update(ID id, T entity);

    void deleteById(ID id);

}

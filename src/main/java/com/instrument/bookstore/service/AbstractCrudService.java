package com.instrument.bookstore.service;

import com.google.common.collect.Lists;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

/**
 * This is a base class for all CRUD services.
 *
 * @param <T> entity type
 * @param <ID> entity's id type
 * @param <R> entity's repository type
 */
@Transactional
public abstract class AbstractCrudService<T, ID, R extends PagingAndSortingRepository<T, ID>> implements CrudService<T, ID> {

    private final R repository;

    protected AbstractCrudService(R repository) {
        this.repository = repository;
    }

    public R getRepository() {
        return repository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<T> findAll() {
        return Lists.newArrayList(getRepository().findAll());
    }

    @Transactional(readOnly = true)
    @Override
    public Page<T> findAll(Integer page, Integer size, String sortBy) {
        Pageable paging = PageRequest.of(page, size, Sort.by(sortBy));
        return getRepository().findAll(paging);
    }

    @Transactional(readOnly = true)
    @Override
    public T findById(ID id) {
        return getRepository().findById(id).orElse(null);
    }

    @Override
    public T create(T entity) {
        return performCreate(entity);
    }

    protected T performCreate(T entity) {
        Assert.notNull(entity, "Entity can't be null");
        beforeCreate(entity);
        T savedEntity = getRepository().save(entity);
        return afterCreate(savedEntity);
    }

    /*
        Hook method that is invoked before entity is created
     */
    protected void beforeCreate(T entity) {
    }

    /*
        Hook method that is invoked after entity is created
     */
    protected T afterCreate(T savedEntity) {
        return savedEntity;
    }

    @Override
    public T update(ID id, T entity) {
        T existingEntity = findById(id);
        return performUpdate(existingEntity, entity);
    }

    protected T performUpdate(T existingEntity, T entity) {
        if (existingEntity == null) { // entity not found
            return null;
        }
        beforeUpdate(existingEntity, entity);
        updateState(existingEntity, entity);
        T updatedEntity = getRepository().save(existingEntity);
        return afterUpdate(updatedEntity);
    }

    /*
        Hook method that is invoked before entity is updated
     */
    protected void beforeUpdate(T existingEntity, T entity) {
    }

    /*
        Hook method that is invoked before entity is updated
     */
    protected T afterUpdate(T updatedEntity) {
        return updatedEntity;
    }

    /*
        Hook method to copy state from provided entity to existing entity before it is saved
     */
    protected abstract void updateState(T existingEntity, T entity);

    @Override
    public void deleteById(ID id) {
        getRepository().deleteById(id);
    }
}

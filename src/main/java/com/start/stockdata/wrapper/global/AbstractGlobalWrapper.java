package com.start.stockdata.wrapper.global;

import com.start.stockdata.identity.model.AbstractEntity;
import com.start.stockdata.repository.AbstractEntityRepo;

import java.util.List;
import java.util.Optional;

public abstract class AbstractGlobalWrapper<
        E extends AbstractEntity,
        R extends AbstractEntityRepo<E>
        > implements GlobalWrapper<E, Long> {

    protected final R repository;

    public AbstractGlobalWrapper(R repository) {
        this.repository = repository;
    }

    @Override
    public E save(E entity) {
        return repository.save(entity);
    }

    @Override
    public E update(E entity) {
        return repository.save(entity);
    }

    @Override
    public Optional<E> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<E> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public Long count(boolean includeDeleted) {
        return repository.count();
    }

}

package com.start.stockdata.wrapper.attributes;

import com.start.stockdata.identity.converter.active.ServiceConverter;
import com.start.stockdata.identity.model.AbstractEntity;
import com.start.stockdata.repository.AbstractEntityRepo;

import java.util.List;
import java.util.Optional;

public abstract class AbstractAttributeWrapper<
        E extends AbstractEntity,
        R extends AbstractEntityRepo<E>
        > implements AttributeWrapper<E, Long> {


    protected final ServiceConverter<E, ?, ?> serviceConverter;
    protected final R repository;

    public AbstractAttributeWrapper(ServiceConverter<E, ?, ?> serviceConverter, R repository) {
        this.serviceConverter = serviceConverter;
        this.repository = repository;
    }

    /*@Override
    public E save(A requestDto) {
        return repository.save(serviceConverter.toEntity(requestDto));
    }

    @Override
    public E update(Long id, A requestDto) {
        E entity = serviceConverter.toEntity(requestDto);
        entity.setId(id);
        return repository.save(entity);
    }*/

 /*   @Override
    public void delete(Long id) {
         repository.deleteById(id);
    }*/

    @Override
    public Optional<E> findById(Long fieldId) {
        return repository.findById(fieldId);
    }

    @Override
    public abstract List<E> findAllByMainEntityId(Long mainEntityId);

    @Override
    public abstract void deleteAllByMainEntityId(Long mainEntityId);

    @Override
    public abstract Long count(Long mainEntityId, boolean includeDeleted);

}

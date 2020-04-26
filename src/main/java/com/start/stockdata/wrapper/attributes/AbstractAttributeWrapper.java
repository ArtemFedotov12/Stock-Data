package com.start.stockdata.wrapper.attributes;

import com.start.stockdata.identity.converter.active.ServiceConverter;
import com.start.stockdata.identity.dto.active.AbstractActiveDto;
import com.start.stockdata.identity.model.AbstractEntity;
import com.start.stockdata.repository.AbstractEntityRepo;

import java.util.List;

public abstract class AbstractAttributeWrapper<
        E extends AbstractEntity,
        A extends AbstractActiveDto,
        R extends AbstractEntityRepo<E>
        > implements AttributeWrapper<E, A, R, Long> {


    protected final ServiceConverter<A, E, ?, ?> serviceConverter;
    protected final R repository;

    public AbstractAttributeWrapper(ServiceConverter<A, E, ?, ?> serviceConverter, R repository) {
        this.serviceConverter = serviceConverter;
        this.repository = repository;
    }

    @Override
    public E save(A requestDto) {
        return repository.save(serviceConverter.toEntity(requestDto));
    }

    @Override
    public E update(Long id, A requestDto) {
        return null;
    }

    @Override
    public E delete(Long id) {
        return null;
    }

    @Override
    public List<E> deleteAllByCompanyId(Long mainEntityId) {
        return null;
    }

    @Override
    public E findById(Long fieldId) {
        return null;
    }

    @Override
    public List<E> findAllByCompanyId(Long mainEntityId) {
        return null;
    }

    @Override
    public Long count(boolean includeDeleted) {
        return null;
    }

}

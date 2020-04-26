package com.start.stockdata.wrapper.global;

import com.start.stockdata.identity.converter.request.RequestConverter;
import com.start.stockdata.identity.dto.request.AbstractRequestDto;
import com.start.stockdata.identity.model.AbstractEntity;
import com.start.stockdata.repository.AbstractEntityRepo;

import java.util.List;
import java.util.Optional;

public abstract class AbstractEntityDtoWrapper<
        E extends AbstractEntity,
        RQ extends AbstractRequestDto,
        R extends AbstractEntityRepo<E>
        > implements StockWrapper<E, RQ, R, Long> {

    protected final RequestConverter<E, RQ> requestConverter;
    protected final R repository;

    public AbstractEntityDtoWrapper(RequestConverter<E, RQ> requestConverter, R repository) {
        this.requestConverter = requestConverter;
        this.repository = repository;
    }

    @Override
    public E save(RQ dto) {
        return repository.save(requestConverter.toEntity(dto));
    }

    @Override
    public E update(final Long id, RQ dto) {
        E entity = requestConverter.toEntity(dto);
        entity.setId(id);
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
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Long count(boolean includeDeleted) {
        return repository.count();
    }

}

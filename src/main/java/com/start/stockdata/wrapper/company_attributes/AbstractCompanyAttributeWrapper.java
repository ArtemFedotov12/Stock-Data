package com.start.stockdata.wrapper.company_attributes;

import com.start.stockdata.identity.converter.request_to_entity.RequestConverter;
import com.start.stockdata.identity.dto.request.AbstractRequestDto;
import com.start.stockdata.identity.model.AbstractEntity;
import com.start.stockdata.repository.AbstractEntityRepo;

import java.util.List;

public abstract class AbstractCompanyAttributeWrapper<
        E extends AbstractEntity,
        RQ extends AbstractRequestDto,
        R extends AbstractEntityRepo<E>,
        T // Long or UUID(String)
        > implements CompanyAttributeWrapper<E, RQ, R, T> {


    protected final RequestConverter<E, RQ> requestConverter;
    protected final R repository;

    public AbstractCompanyAttributeWrapper(RequestConverter<E, RQ> requestConverter, R repository) {
        this.requestConverter = requestConverter;
        this.repository = repository;
    }

    @Override
    public E save(T companyId, RQ requestDto) {
        return null;
    }

    @Override
    public E update(T id, RQ requestDto) {
        return null;
    }

    @Override
    public E delete(T id) {
        return null;
    }

    @Override
    public List<E> deleteAllByCompanyId(T companyId) {
        return null;
    }

    @Override
    public E findById(T fieldId) {
        return null;
    }

    @Override
    public List<E> findAllByCompanyId(T companyId) {
        return null;
    }

    @Override
    public T count(boolean includeDeleted) {
        return null;
    }
}

package com.start.stockdata.service.company_attribute;

import com.start.stockdata.exception.exception.EntityAlreadyExistsException;
import com.start.stockdata.identity.converter.entity_to_dto.ResponseConverter;
import com.start.stockdata.identity.dto.request.AbstractRequestDto;
import com.start.stockdata.identity.dto.response.AbstractResponseDto;
import com.start.stockdata.identity.model.AbstractEntity;
import com.start.stockdata.wrapper.company_attributes.CompanyAttributeWrapper;

import java.util.List;

public abstract class AbstractCompanyAttributeService<
        E extends AbstractEntity,
        RS extends AbstractResponseDto,
        RQ extends AbstractRequestDto,
        WR extends CompanyAttributeWrapper<E, RQ, ?, T>,
        T
        >
        implements CompanyAttributeService<E, RS, RQ, WR, T> {

    protected final WR wrapper;
    protected final ResponseConverter<E, RS> converter;

    public AbstractCompanyAttributeService(WR wrapper, ResponseConverter<E, RS> converter) {
        this.wrapper = wrapper;
        this.converter = converter;
    }

    @Override
    public RS save(T companyId, RQ requestDto) {
        if (entityAlreadyExistsSave(companyId, requestDto)) {
            throw new EntityAlreadyExistsException(requestDto);
        }
        E entity = wrapper.save(companyId, requestDto);
        return converter.toDto(entity);
    }

    @Override
    public RS update(T id, RQ requestDto) {
        return null;
    }

    @Override
    public RS delete(T id) {
        return null;
    }

    @Override
    public List<RS> deleteAllByCompanyId(T companyId) {
        return null;
    }

    @Override
    public RS findById(T fieldId) {
        return null;
    }

    @Override
    public List<RS> findAllByCompanyId(T companyId) {
        return null;
    }

    @Override
    public T count(boolean includeDeleted) {
        return null;
    }

    protected abstract boolean entityAlreadyExistsSave(T companyId, RQ requestDto);

    //Entity that must be updated, must be excluded from check
    protected abstract boolean entityAlreadyExistsUpdate(final T id, RQ requestDto);

}

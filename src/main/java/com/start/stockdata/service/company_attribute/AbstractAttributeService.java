package com.start.stockdata.service.company_attribute;

import com.start.stockdata.exception.exception.EntityAlreadyExistsException;
import com.start.stockdata.exception.exception.MainEntityNotFoundException;
import com.start.stockdata.identity.converter.active.ServiceConverter;
import com.start.stockdata.identity.converter.response.ResponseConverter;
import com.start.stockdata.identity.dto.active.AbstractActiveDto;
import com.start.stockdata.identity.dto.request.AbstractRequestDto;
import com.start.stockdata.identity.dto.response.AbstractResponseDto;
import com.start.stockdata.identity.model.AbstractEntity;
import com.start.stockdata.wrapper.attributes.AttributeWrapper;
import com.start.stockdata.wrapper.global.StockWrapper;

import java.util.List;
import java.util.Optional;

public abstract class AbstractAttributeService<
        E extends AbstractEntity,
        A extends AbstractActiveDto,
        RQ extends AbstractRequestDto,
        RS extends AbstractResponseDto,
        WR extends AttributeWrapper<E, A, ?, Long>,
        M extends StockWrapper<?, ?, ?, Long>
        >
        implements AttributeService<RQ, RS, Long> {

    protected final WR attributeWrapper;
    protected final M mainEntityWrapper;
    protected final ResponseConverter<E, RS> responseConverter;
    protected final ServiceConverter<A, E, RQ, RS> serviceConverter;

    public AbstractAttributeService(WR attributeWrapper, M mainEntityWrapper, ResponseConverter<E, RS> responseConverter, ServiceConverter<A, E, RQ, RS> serviceConverter) {
        this.attributeWrapper = attributeWrapper;
        this.mainEntityWrapper = mainEntityWrapper;
        this.responseConverter = responseConverter;
        this.serviceConverter = serviceConverter;
    }

    @Override
    public RS save(Long mainEntityId, RQ requestDto) {
        if (isMainEntityExists(mainEntityId)) {
            throw new MainEntityNotFoundException(mainEntityId.toString());
        }

        if (entityAlreadyExistsSave(mainEntityId, requestDto)) {
            throw new EntityAlreadyExistsException(requestDto);
        }
        E entity = attributeWrapper.save(convertToActiveDto(mainEntityId, requestDto));
        return responseConverter.toDto(entity);
    }

    private boolean isMainEntityExists(Long mainEntityId) {
        Optional optionalCompany = mainEntityWrapper.findById(mainEntityId);
        return optionalCompany.isPresent();
    }

    @Override
    public RS update(Long mainEntityId, Long id, RQ requestDto) {
        if (entityAlreadyExistsUpdate(mainEntityId, mainEntityId, requestDto)) {
            throw new EntityAlreadyExistsException(requestDto);
        }
        E entity = attributeWrapper.update(id, convertToActiveDto(mainEntityId, requestDto));
        return responseConverter.toDto(entity);
    }

    @Override
    public RS delete(Long mainEntityId, Long id) {
        return null;
    }

    @Override
    public List<RS> deleteAllByCompanyId(Long mainEntityId) {
        return null;
    }

    @Override
    public RS findById(Long mainEntityId, Long fieldId) {
        return null;
    }

    @Override
    public List<RS> findAllByCompanyId(Long mainEntityId) {
        return null;
    }

    @Override
    public Long count(Long companyId, boolean includeDeleted) {
        return null;
    }


    protected abstract A convertToActiveDto(Long companyId, RQ requestDto);

    protected abstract boolean entityAlreadyExistsSave(Long companyId, RQ requestDto);

    //Entity that must be updated, must be excluded from check list
    protected abstract boolean entityAlreadyExistsUpdate(Long companyId, final Long id, RQ requestDto);

}

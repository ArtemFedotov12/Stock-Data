package com.start.stockdata.service.company_attribute;

import com.start.stockdata.exception.exception.*;
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
import java.util.stream.Collectors;

public abstract class AbstractAttributeService<
        E extends AbstractEntity,
        A extends AbstractActiveDto,
        RQ extends AbstractRequestDto,
        RS extends AbstractResponseDto,
        WR extends AttributeWrapper<E, A, Long>,
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
        if (!isMainEntityExists(mainEntityId)) {
            throw new MainEntityNotFoundException(mainEntityId.toString());
        }

        additionalCheck(mainEntityId);
        // check if such entity exists within "mainEntity",
        // not in the whole DB!!
        if (entityAlreadyExists(mainEntityId, requestDto)) {
            throw new EntityWithinMainEntityAlreadyExistException(String.valueOf(mainEntityId), requestDto);
        }

        E entity = attributeWrapper.save(convertToActiveDto(mainEntityId, requestDto));
        return serviceConverter.toDto(entity);
    }


    @Override
    public RS update(Long mainEntityId, Long id, RQ requestDto) {
        if (!isMainEntityExists(mainEntityId)) {
            throw new MainEntityNotFoundException(mainEntityId.toString());
        }

        additionalCheck(mainEntityId, id);

        if (entityAlreadyExistsUpdate(mainEntityId, id, requestDto)) {
            throw new EntityWithinMainEntityAlreadyExistException(String.valueOf(mainEntityId), requestDto);
        }

        E entity = attributeWrapper.update(id, convertToActiveDto(mainEntityId, requestDto));
        return serviceConverter.toDto(entity);
    }

    @Override
    public RS delete(Long mainEntityId, Long id) {
        if (!isMainEntityExists(mainEntityId)) {
            throw new MainEntityNotFoundException(mainEntityId.toString());
        }

        additionalCheck(mainEntityId, id);

        Optional<E> optionalEntity = attributeWrapper.findById(id);
        if (optionalEntity.isPresent()) {
            attributeWrapper.delete(id);
            return serviceConverter.toDto(optionalEntity.get());
        } else {
            throw new DeletionEntityByIdNotFoundException(String.valueOf(id));
        }

    }

    @Override
    public List<RS> deleteAllByCompanyId(Long mainEntityId) {
        if (!isMainEntityExists(mainEntityId)) {
            throw new MainEntityNotFoundException(mainEntityId.toString());
        }

        additionalCheck(mainEntityId);

        List<E> allByCompanyId = attributeWrapper.findAllByCompanyId(mainEntityId);
        if (!allByCompanyId.isEmpty()) {
            attributeWrapper.deleteAllByCompanyId(mainEntityId);
            return convert(allByCompanyId);
        } else {
            throw new DeletionByIdMainEntityNotFoundException(String.valueOf(mainEntityId));
        }


    }

    @Override
    public RS findById(Long mainEntityId, Long id) {
        if (!isMainEntityExists(mainEntityId)) {
            throw new MainEntityNotFoundException(mainEntityId.toString());
        }

        additionalCheck(mainEntityId, id);

        Optional<E> entityOptional = attributeWrapper.findById(id);
        if (entityOptional.isPresent()) {
            return serviceConverter.toDto(entityOptional.get());
        } else {
            throw new EntityByIdNotFoundException(id);
        }
    }

    @Override
    public List<RS> findAllByCompanyId(Long mainEntityId) {
        if (!isMainEntityExists(mainEntityId)) {
            throw new MainEntityNotFoundException(mainEntityId.toString());
        }

        additionalCheck(mainEntityId);

        return convert(attributeWrapper.findAllByCompanyId(mainEntityId));
    }

    @Override
    public Long count(Long mainEntityId, boolean includeDeleted) {
        if (!isMainEntityExists(mainEntityId)) {
            throw new MainEntityNotFoundException(mainEntityId.toString());
        }

        additionalCheck(mainEntityId);

        return attributeWrapper.count(mainEntityId, includeDeleted);
    }


    protected abstract A convertToActiveDto(Long companyId, RQ requestDto);

    protected abstract boolean entityAlreadyExists(Long companyId, RQ requestDto);

    //Entity that must be updated, must be excluded from check list
    protected abstract boolean entityAlreadyExistsUpdate(Long companyId, final Long id, RQ requestDto);

    private boolean isMainEntityExists(Long mainEntityId) {
        Optional<?> optionalCompany = mainEntityWrapper.findById(mainEntityId);
        return optionalCompany.isPresent();
    }

    protected List<RS> convert(List<E> entityList) {
        return entityList.stream()
                .map(serviceConverter::toDto)
                .collect(Collectors.toList());
    }

    protected abstract void additionalCheck(Long mainEntityId, Long id);

    protected abstract void additionalCheck(Long mainEntityId);
}

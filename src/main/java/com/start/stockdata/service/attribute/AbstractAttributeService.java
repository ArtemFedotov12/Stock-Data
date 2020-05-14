package com.start.stockdata.service.attribute;

import com.start.stockdata.exception.exception.DeletionByIdMainEntityNotFoundException;
import com.start.stockdata.exception.exception.DeletionEntityByIdNotFoundException;
import com.start.stockdata.exception.exception.EntityByIdNotFoundException;
import com.start.stockdata.identity.converter.request.RequestConverter;
import com.start.stockdata.identity.converter.response.ResponseConverter;
import com.start.stockdata.identity.dto.request.AbstractRequestDto;
import com.start.stockdata.identity.dto.response.AbstractResponseDto;
import com.start.stockdata.identity.model.AbstractEntity;
import com.start.stockdata.validator.attribute.AttributeValidator;
import com.start.stockdata.wrapper.attributes.AttributeWrapper;
import com.start.stockdata.wrapper.global.GlobalWrapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
public abstract class AbstractAttributeService<
        E extends AbstractEntity,
        RQ extends AbstractRequestDto,
        RS extends AbstractResponseDto,
        WR extends AttributeWrapper<E, Long>,
        M extends GlobalWrapper<?, Long>
        >
        implements AttributeService<RQ, RS, Long> {

    protected final WR attributeWrapper;
    protected final M mainEntityWrapper;
    protected final ResponseConverter<E, RS> responseConverter;
    protected final RequestConverter<E, RQ> requestConverter;
    protected final AttributeValidator<RQ, Long> attributeValidator;

    public AbstractAttributeService(WR attributeWrapper, M mainEntityWrapper, ResponseConverter<E, RS> responseConverter, RequestConverter<E, RQ> requestConverter, AttributeValidator<RQ, Long> attributeValidator) {
        this.attributeWrapper = attributeWrapper;
        this.mainEntityWrapper = mainEntityWrapper;
        this.responseConverter = responseConverter;
        this.requestConverter = requestConverter;
        this.attributeValidator = attributeValidator;
    }

    @Override
    public RS save(Long mainEntityId, RQ requestDto) {
        // check if such entity exists within "mainEntity",
        // not in the whole DB!!
        attributeValidator.validate(mainEntityId, requestDto);
        E entity = attributeWrapper.save(mainEntityId, requestConverter.toEntity(requestDto));
        return responseConverter.toDto(entity);
    }


    @Override
    public RS update(Long mainEntityId, Long id, RQ requestDto) {
        // exclude itself to check on uniqueness
        attributeValidator.validate(mainEntityId, id, requestDto);
        E entity = attributeWrapper.update(mainEntityId, id, requestConverter.toEntity(requestDto));
        return responseConverter.toDto(entity);
    }


    @Override
    public RS delete(Long mainEntityId, Long id) {

        attributeValidator.validate(mainEntityId, id);

        Optional<E> optionalEntity = attributeWrapper.findById(id);
        if (optionalEntity.isPresent()) {
            attributeWrapper.delete(mainEntityId, optionalEntity.get());
            return responseConverter.toDto(optionalEntity.get());
        } else {
            throw new DeletionEntityByIdNotFoundException(String.valueOf(id));
        }

    }


    @Override
    public List<RS> deleteAllByMainEntityId(Long mainEntityId) {

        attributeValidator.validate(mainEntityId);

        List<E> companyFields = attributeWrapper.findAllByMainEntityId(mainEntityId);
        if (!companyFields.isEmpty()) {
            attributeWrapper.deleteAllByMainEntityId(mainEntityId);
            return convert(companyFields);
        } else {
            throw new DeletionByIdMainEntityNotFoundException(String.valueOf(mainEntityId));
        }

    }

    @Override
    public RS findById(Long mainEntityId, Long id) {

        attributeValidator.validate(mainEntityId, id);

        Optional<E> entityOptional = attributeWrapper.findById(id);
        if (entityOptional.isPresent()) {
            return responseConverter.toDto(entityOptional.get());
        } else {
            throw new EntityByIdNotFoundException(id);
        }
    }

    @Override
    public List<RS> findAllByMainEntityId(Long mainEntityId) {
        attributeValidator.validate(mainEntityId);
        return convert(attributeWrapper.findAllByMainEntityId(mainEntityId));
    }

    @Override
    public Long count(Long mainEntityId, boolean includeDeleted) {
        attributeValidator.validate(mainEntityId);
        return attributeWrapper.count(mainEntityId, includeDeleted);
    }


    protected List<RS> convert(List<E> entityList) {
        return entityList.stream()
                .map(responseConverter::toDto)
                .collect(Collectors.toList());
    }

}

package com.start.stockdata.service.attribute;

import com.start.stockdata.identity.converter.request.RequestConverter;
import com.start.stockdata.identity.converter.response.ResponseConverter;
import com.start.stockdata.identity.dto.request.FieldRequestDto;
import com.start.stockdata.identity.dto.response.FieldResponseDto;
import com.start.stockdata.identity.model.Field;
import com.start.stockdata.validator.attribute.AttributeValidator;
import com.start.stockdata.validator.attribute.FieldValidator;
import com.start.stockdata.wrapper.attributes.FieldWrapper;
import com.start.stockdata.wrapper.global.CompanyWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class DefaultFieldService extends AbstractAttributeService<
        Field,
        FieldRequestDto,
        FieldResponseDto,
        FieldWrapper,
        CompanyWrapper
        > implements FieldService<FieldRequestDto, FieldResponseDto, Long> {

    private final FieldValidator<FieldRequestDto> fieldValidator;

    public DefaultFieldService(FieldWrapper attributeWrapper, CompanyWrapper mainEntityWrapper, ResponseConverter<Field, FieldResponseDto> responseConverter, RequestConverter<Field, FieldRequestDto> requestConverter, AttributeValidator<FieldRequestDto, Long> attributeValidator, FieldValidator<FieldRequestDto> fieldValidator) {
        super(attributeWrapper, mainEntityWrapper, responseConverter, requestConverter, attributeValidator);
        this.fieldValidator = fieldValidator;
    }

}

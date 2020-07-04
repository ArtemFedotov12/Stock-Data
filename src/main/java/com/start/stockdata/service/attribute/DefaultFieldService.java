package com.start.stockdata.service.attribute;

import com.start.stockdata.identity.converter.request.RequestConverter;
import com.start.stockdata.identity.converter.response.ResponseConverter;
import com.start.stockdata.identity.dto.request.FieldRequestDto;
import com.start.stockdata.identity.dto.response.FieldResponseDto;
import com.start.stockdata.identity.model.Field;
import com.start.stockdata.validator.attribute.AttributeValidator;
import com.start.stockdata.wrapper.attributes.field.DefaultFieldWrapper;
import com.start.stockdata.wrapper.global.company.DefaultCompanyWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class DefaultFieldService extends AbstractAttributeService<
        Field,
        FieldRequestDto,
        FieldResponseDto,
        DefaultFieldWrapper,
        DefaultCompanyWrapper
        > implements FieldService{

    public DefaultFieldService(DefaultFieldWrapper attributeWrapper, DefaultCompanyWrapper mainEntityWrapper, ResponseConverter<Field, FieldResponseDto> responseConverter, RequestConverter<Field, FieldRequestDto> requestConverter, AttributeValidator<FieldRequestDto, Long> attributeValidator) {
        super(attributeWrapper, mainEntityWrapper, responseConverter, requestConverter, attributeValidator);
    }



}

package com.start.stockdata.wrapper.attributes;

import com.start.stockdata.identity.converter.active.ServiceConverter;
import com.start.stockdata.identity.dto.active.CompanyFieldActiveDto;
import com.start.stockdata.identity.model.CompanyField;
import com.start.stockdata.repository.CompanyFieldRepo;
import org.springframework.stereotype.Component;

@Component
public class FieldWrapper extends AbstractAttributeWrapper<
        CompanyField,
        CompanyFieldActiveDto,
        CompanyFieldRepo
        > {

    public FieldWrapper(ServiceConverter<CompanyFieldActiveDto, CompanyField, ?, ?> serviceConverter, CompanyFieldRepo repository) {
        super(serviceConverter, repository);
    }

}

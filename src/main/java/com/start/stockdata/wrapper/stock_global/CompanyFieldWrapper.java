package com.start.stockdata.wrapper.stock_global;

import com.start.stockdata.identity.converter.request_to_entity.RequestConverter;
import com.start.stockdata.identity.dto.request.CompanyFieldRequestDto;
import com.start.stockdata.identity.model.CompanyField;
import com.start.stockdata.repository.CompanyFieldRepo;
import org.springframework.stereotype.Component;

@Component
public class CompanyFieldWrapper extends AbstractEntityDtoWrapper<
        CompanyField,
        CompanyFieldRequestDto,
        CompanyFieldRepo
        > {

    public CompanyFieldWrapper(
            RequestConverter<CompanyField, CompanyFieldRequestDto> requestConverter,
            CompanyFieldRepo repository) {

        super(requestConverter, repository);
    }
}

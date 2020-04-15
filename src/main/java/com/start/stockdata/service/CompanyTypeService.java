package com.start.stockdata.service;

import com.start.stockdata.identity.converter.creation_dto_to_simple_dto.RequestConverter;
import com.start.stockdata.identity.dto.request.CompanyTypeRequestDto;
import com.start.stockdata.identity.dto.response.CompanyTypeDto;
import com.start.stockdata.wrapper.CompanyTypeWrapper;
import org.springframework.stereotype.Component;

@Component
public class CompanyTypeService extends AbstractService<CompanyTypeRequestDto, CompanyTypeDto, CompanyTypeWrapper> {

    public CompanyTypeService(CompanyTypeWrapper wrapper, RequestConverter<CompanyTypeRequestDto, CompanyTypeDto> converter) {
        super(wrapper, converter);
    }

}

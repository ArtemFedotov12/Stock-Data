package com.start.stockdata.wrapper.stock_global;

import com.start.stockdata.identity.converter.request_to_entity.RequestConverter;
import com.start.stockdata.identity.dto.request.CompanyRequestDto;
import com.start.stockdata.identity.model.Company;
import com.start.stockdata.repository.CompanyRepo;
import org.springframework.stereotype.Component;

@Component
public class CompanyFullWrapper extends AbstractEntityDtoWrapper<Company, CompanyRequestDto,CompanyRepo>{


    public CompanyFullWrapper(RequestConverter<Company, CompanyRequestDto> requestConverter, CompanyRepo repository) {
        super(requestConverter, repository);
    }
}

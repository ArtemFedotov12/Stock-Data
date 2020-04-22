package com.start.stockdata.wrapper;

import com.start.stockdata.identity.converter.entity_to_dto.ResponseConverter;
import com.start.stockdata.identity.converter.request_to_entity.RequestConverter;
import com.start.stockdata.identity.dto.request.CompanyRequestDto;
import com.start.stockdata.identity.dto.response.CompanyFullResponseDto;
import com.start.stockdata.identity.model.Company;
import com.start.stockdata.repository.CompanyRepo;
import org.springframework.stereotype.Component;

@Component
public class CompanyFullWrapper extends AbstractEntityDtoWrapper<Company, CompanyFullResponseDto, CompanyRequestDto,CompanyRepo>{

    public CompanyFullWrapper(ResponseConverter<Company, CompanyFullResponseDto> responseConverter,
                              RequestConverter<Company, CompanyRequestDto> requestConverter,
                              CompanyRepo repository) {
        super(responseConverter, requestConverter, repository);
    }
}

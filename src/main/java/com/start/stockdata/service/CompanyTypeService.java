package com.start.stockdata.service;

import com.start.stockdata.identity.dto.request.CompanyTypeRequestDto;
import com.start.stockdata.identity.dto.response.CompanyTypeResponseDto;
import com.start.stockdata.identity.model.CompanyType;
import com.start.stockdata.wrapper.CompanyTypeWrapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CompanyTypeService extends AbstractService<
        CompanyTypeRequestDto,
        CompanyTypeResponseDto,
        CompanyTypeWrapper> {

    public CompanyTypeService(CompanyTypeWrapper wrapper) {
        super(wrapper);
    }

    @Override
    protected boolean entityAlreadyExistsSave(CompanyTypeRequestDto requestDto) {
        Optional<CompanyType> companyTypeOptional = wrapper.findByType(requestDto.getType());
        return companyTypeOptional.isPresent();
    }

    @Override
    protected boolean entityAlreadyExistsUpdate(final Long id, CompanyTypeRequestDto requestDto) {
        Optional<CompanyType> companyTypeOptional = wrapper.findByType(requestDto.getType());
        return false;
    }
}

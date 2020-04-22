package com.start.stockdata.service;

import com.start.stockdata.exception.exception.EntityAlreadyExistsException;
import com.start.stockdata.identity.converter.entity_to_dto.ResponseConverter;
import com.start.stockdata.identity.dto.request.CompanyRequestDto;
import com.start.stockdata.identity.dto.response.CompanyResponseDto;
import com.start.stockdata.identity.model.Company;
import com.start.stockdata.wrapper.CompanyWrapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService extends AbstractService<Company, CompanyRequestDto, CompanyResponseDto, CompanyWrapper> {

    public CompanyService(CompanyWrapper wrapper, ResponseConverter<Company, CompanyResponseDto> converter) {
        super(wrapper, converter);
    }

    @Override
    public CompanyResponseDto save(CompanyRequestDto companyRequestDto) {
        if (entityAlreadyExistsSave(companyRequestDto)) {
            throw new EntityAlreadyExistsException(companyRequestDto);
        }

        return converter.toDto(wrapper.save(companyRequestDto));
    }

    public List<CompanyResponseDto> findAllByUserId() {
        return convert(wrapper.findAllByUserId());
    }

    @Override
    protected boolean entityAlreadyExistsSave(CompanyRequestDto requestDto) {
        return false;
    }

    @Override
    protected boolean entityAlreadyExistsUpdate(final Long id, CompanyRequestDto requestDto) {
        return false;
    }
}

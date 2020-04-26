package com.start.stockdata.service.company_attribute;

import com.start.stockdata.exception.exception.CompanyByIdNotFoundException;
import com.start.stockdata.identity.converter.active.ServiceConverter;
import com.start.stockdata.identity.converter.response.ResponseConverter;
import com.start.stockdata.identity.dto.active.CompanyFieldActiveDto;
import com.start.stockdata.identity.dto.request.CompanyFieldRequestDto;
import com.start.stockdata.identity.dto.response.CompanyFieldResponseDto;
import com.start.stockdata.identity.model.Company;
import com.start.stockdata.identity.model.CompanyField;
import com.start.stockdata.wrapper.attributes.FieldWrapper;
import com.start.stockdata.wrapper.global.CompanyWrapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FieldService extends AbstractAttributeService<
        CompanyField,
        CompanyFieldActiveDto,
        CompanyFieldRequestDto,
        CompanyFieldResponseDto,
        FieldWrapper,
        CompanyWrapper
        > {

    public FieldService(FieldWrapper attributeWrapper, CompanyWrapper mainEntityWrapper, ResponseConverter<CompanyField, CompanyFieldResponseDto> responseConverter, ServiceConverter<CompanyFieldActiveDto, CompanyField, CompanyFieldRequestDto, CompanyFieldResponseDto> serviceConverter) {
        super(attributeWrapper, mainEntityWrapper, responseConverter, serviceConverter);
    }



    @Override
    protected boolean entityAlreadyExistsSave(Long companyId, CompanyFieldRequestDto requestDto) {
        return false;
    }

    @Override
    protected boolean entityAlreadyExistsUpdate(Long companyId, Long id, CompanyFieldRequestDto requestDto) {
        return false;
    }

        @Override
    protected CompanyFieldActiveDto convertToActiveDto(Long companyId, CompanyFieldRequestDto requestDto) {
        CompanyFieldActiveDto companyFieldActiveDto = serviceConverter.toActive(requestDto);
        Optional<Company> optionalCompany = mainEntityWrapper.findById(companyId);

        if (!optionalCompany.isPresent()) {
            throw new CompanyByIdNotFoundException(companyId);
        } else {
            companyFieldActiveDto.setCompany(optionalCompany.get());
            return companyFieldActiveDto;
        }
    }
/*
    @Override
    protected boolean entityAlreadyExistsSave(Long companyId, CompanyFieldRequestDto requestDto) {
        Optional<Company> optionalCompany = companyWrapper.findById(companyId);
        if (!optionalCompany.isPresent()) {
            throw new CompanyByIdNotFoundException(companyId);
        } else {
            Set<CompanyField> fieldSet = optionalCompany.get().getCompanyFields();
            CompanyField companyField = serviceConverter.toEntity(requestDto);

            return fieldSet
                    .stream()
                    .anyMatch(item -> item.equals(companyField));
        }

    }*/


}

package com.start.stockdata.identity.converter.request;

import com.start.stockdata.identity.dto.request.CompanyFactorRequestDto;
import com.start.stockdata.identity.dto.request.CompanyFieldRequestDto;
import com.start.stockdata.identity.dto.request.CompanyRequestDto;
import com.start.stockdata.identity.model.Company;
import com.start.stockdata.identity.model.CompanyFactor;
import com.start.stockdata.identity.model.CompanyField;
import com.start.stockdata.identity.model.CompanyType;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@Component
public class CompanyRequestConverter implements RequestConverter<Company, CompanyRequestDto>  {

    private final RequestConverter<CompanyFactor, CompanyFactorRequestDto> factorConverter;
    private  final RequestConverter<CompanyField, CompanyFieldRequestDto> fieldConverter;

    public CompanyRequestConverter(RequestConverter<CompanyFactor, CompanyFactorRequestDto> factorConverter,
                                   RequestConverter<CompanyField, CompanyFieldRequestDto> fieldConverter) {
        this.factorConverter = factorConverter;
        this.fieldConverter = fieldConverter;
    }

    @Override
    public Company toEntity(CompanyRequestDto requestDto) {
        return ofNullable(requestDto)
                .map(item -> {
                    Company company = new Company();
                    company.setName(item.getName());
                    company.setCompanyTypes(item
                            .getTypes()
                            .stream()
                            .map(companyTypeIdDto -> {
                                CompanyType companyType = new CompanyType();
                                companyType.setId(companyTypeIdDto.getId());
                                return companyType;
                            })
                            .collect(Collectors.toSet()));
                    company.setCompanyFields(item
                            .getFields()
                            .stream()
                            .map(fieldConverter::toEntity)
                            .collect(Collectors.toSet()));
                    company.setCompanyFactors(item
                            .getFactors()
                            .stream()
                            .map(factorConverter::toEntity)
                            .collect(Collectors.toSet()));
                    return company;
                })
                .orElse(null);
    }
}

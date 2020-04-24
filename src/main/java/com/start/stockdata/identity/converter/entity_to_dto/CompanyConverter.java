package com.start.stockdata.identity.converter.entity_to_dto;

import com.start.stockdata.identity.dto.different.CompanyTypeIdRequestDto;
import com.start.stockdata.identity.dto.response.CompanyResponseDto;
import com.start.stockdata.identity.model.Company;
import com.start.stockdata.identity.model.CompanyType;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@Component
public class CompanyConverter implements ResponseConverter<Company, CompanyResponseDto> {

    private final CompanyFieldConverter companyFieldConverter;
    private final CompanyFactorConverter companyFactorConverter;
    private final CompanyTypeConverter companyTypeConverter;


    public CompanyConverter(CompanyFieldConverter companyFieldConverter, CompanyFactorConverter companyFactorConverter, CompanyTypeConverter companyTypeConverter) {
        this.companyFieldConverter = companyFieldConverter;
        this.companyFactorConverter = companyFactorConverter;
        this.companyTypeConverter = companyTypeConverter;
    }

    @Override
    public Company toEntity(CompanyResponseDto dto) {
        return ofNullable(dto)
                .map(item -> {
                    Company company = new Company();
                    company.setId(item.getId());
                    company.setName(item.getName());
                    company.setCompanyTypes(item
                            .getTypes()
                            .stream()
                            .map(companyTypeIdRequestDto -> {
                                CompanyType companyType = new CompanyType();
                                companyType.setId(companyTypeIdRequestDto.getId());
                                return companyType;
                            })
                            .collect(Collectors.toSet()));
                    company.setCompanyFields(item
                            .getFields()
                            .stream()
                            .map(companyFieldConverter::toEntity)
                            .collect(Collectors.toSet()));
                    company.setCompanyFactors(item
                            .getFactors()
                            .stream()
                            .map(companyFactorConverter::toEntity)
                            .collect(Collectors.toSet()));
                    company.setUserId(item.getUserId());
                    return company;
                })
                .orElse(null);
    }

    @Override
    public CompanyResponseDto toDto(Company entity) {
        return ofNullable(entity)
                .map(item -> {
                    CompanyResponseDto companyResponseDto = new CompanyResponseDto();
                    companyResponseDto.setId(item.getId());
                    companyResponseDto.setName(item.getName());
                    companyResponseDto.setTypes(item
                            .getCompanyTypes()
                            .stream()
                            .map(companyTypeConverter::toDto)
                            .collect(Collectors.toSet()));
                    companyResponseDto.setFields(item
                            .getCompanyFields()
                            .stream()
                            .map(companyFieldConverter::toDto)
                            .collect(Collectors.toSet()));
                    companyResponseDto.setFactors(item
                            .getCompanyFactors()
                            .stream()
                            .map(companyFactorConverter::toDto)
                            .collect(Collectors.toSet()));
                    companyResponseDto.setUserId(item.getUserId());
                    return companyResponseDto;
                })
                .orElse(null);
    }

}

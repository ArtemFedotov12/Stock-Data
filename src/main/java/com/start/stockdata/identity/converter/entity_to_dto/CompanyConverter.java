package com.start.stockdata.identity.converter.entity_to_dto;

import com.start.stockdata.identity.dto.CompanyDto;
import com.start.stockdata.identity.model.Company;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@Component
public class CompanyConverter implements EntityDtoConverter<Company, CompanyDto> {

    private final CompanyTypeConverter companyTypeConverter;
    private final CompanyFieldConverter companyFieldConverter;
    private final CompanyFactorConverter companyFactorConverter;

    public CompanyConverter(CompanyTypeConverter companyTypeConverter,
                            CompanyFieldConverter companyFieldConverter,
                            CompanyFactorConverter companyFactorConverter) {
        this.companyTypeConverter = companyTypeConverter;
        this.companyFieldConverter = companyFieldConverter;
        this.companyFactorConverter = companyFactorConverter;
    }

    @Override
    public Company toEntity(CompanyDto dto) {
        return ofNullable(dto)
                .map(item -> {
                    Company company = new Company();
                    company.setId(item.getId());
                    company.setName(item.getName());
                    company.setCompanyTypes(item
                            .getTypes()
                            .stream()
                            .map(companyTypeConverter::toEntity)
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
    public CompanyDto toDto(Company entity) {
        return ofNullable(entity)
                .map(item -> {
                    CompanyDto companyDto = new CompanyDto();
                    companyDto.setId(item.getId());
                    companyDto.setName(item.getName());
                    companyDto.setTypes(item
                            .getCompanyTypes()
                            .stream()
                            .map(companyTypeConverter::toDto)
                            .collect(Collectors.toSet()));
                    companyDto.setFields(item
                            .getCompanyFields()
                            .stream()
                            .map(companyFieldConverter::toDto)
                            .collect(Collectors.toSet()));
                    companyDto.setFactors(item
                            .getCompanyFactors()
                            .stream()
                            .map(companyFactorConverter::toDto)
                            .collect(Collectors.toSet()));
                    companyDto.setUserId(item.getUserId());
                    return companyDto;
                })
                .orElse(null);
    }

}

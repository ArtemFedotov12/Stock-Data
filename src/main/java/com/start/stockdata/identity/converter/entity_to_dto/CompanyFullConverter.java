package com.start.stockdata.identity.converter.entity_to_dto;

import com.start.stockdata.identity.dto.different.CompanyTypeIdDto;
import com.start.stockdata.identity.dto.response.CompanyDto;
import com.start.stockdata.identity.dto.response.CompanyFullDto;
import com.start.stockdata.identity.model.Company;
import com.start.stockdata.identity.model.CompanyType;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@Component
public class CompanyFullConverter implements EntityDtoConverter<Company, CompanyFullDto> {


    private final CompanyTypeConverter companyTypeConverter;
    private final CompanyFieldConverter companyFieldConverter;
    private final CompanyFactorConverter companyFactorConverter;

    public CompanyFullConverter(CompanyTypeConverter companyTypeConverter,
                                CompanyFieldConverter companyFieldConverter,
                                CompanyFactorConverter companyFactorConverter) {
        this.companyTypeConverter = companyTypeConverter;
        this.companyFieldConverter = companyFieldConverter;
        this.companyFactorConverter = companyFactorConverter;
    }

    @Override
    public Company toEntity(CompanyFullDto dto) {
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
    public CompanyFullDto toDto(Company entity) {
        return ofNullable(entity)
                .map(item -> {
                    CompanyFullDto companyFullDto = new CompanyFullDto();
                    companyFullDto.setId(item.getId());
                    companyFullDto.setName(item.getName());
                    companyFullDto.setTypes(item
                            .getCompanyTypes()
                            .stream()
                            .map(companyTypeConverter::toDto)
                            .collect(Collectors.toSet()));
                    companyFullDto.setFields(item
                            .getCompanyFields()
                            .stream()
                            .map(companyFieldConverter::toDto)
                            .collect(Collectors.toSet()));
                    companyFullDto.setFactors(item
                            .getCompanyFactors()
                            .stream()
                            .map(companyFactorConverter::toDto)
                            .collect(Collectors.toSet()));
                    companyFullDto.setUserId(item.getUserId());
                    return companyFullDto;
                })
                .orElse(null);
    }
    
}

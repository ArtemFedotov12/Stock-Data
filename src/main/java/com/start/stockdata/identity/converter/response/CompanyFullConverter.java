package com.start.stockdata.identity.converter.response;

import com.start.stockdata.identity.dto.response.CompanyFullResponseDto;
import com.start.stockdata.identity.model.Company;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@Component
public class CompanyFullConverter implements ResponseConverter<Company, CompanyFullResponseDto> {


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
    public Company toEntity(CompanyFullResponseDto dto) {
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
                    company.setFields(item
                            .getFields()
                            .stream()
                            .map(companyFieldConverter::toEntity)
                            .collect(Collectors.toSet()));
                    company.setFactors(item
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
    public CompanyFullResponseDto toDto(Company entity) {
        return ofNullable(entity)
                .map(item -> {
                    CompanyFullResponseDto companyFullResponseDto = new CompanyFullResponseDto();
                    companyFullResponseDto.setId(item.getId());
                    companyFullResponseDto.setName(item.getName());
                    companyFullResponseDto.setTypes(item
                            .getCompanyTypes()
                            .stream()
                            .map(companyTypeConverter::toDto)
                            .collect(Collectors.toSet()));
                    companyFullResponseDto.setFields(item
                            .getFields()
                            .stream()
                            .map(companyFieldConverter::toDto)
                            .collect(Collectors.toSet()));
                    companyFullResponseDto.setFactors(item
                            .getFactors()
                            .stream()
                            .map(companyFactorConverter::toDto)
                            .collect(Collectors.toSet()));
                    companyFullResponseDto.setUserId(item.getUserId());
                    return companyFullResponseDto;
                })
                .orElse(null);
    }
    
}

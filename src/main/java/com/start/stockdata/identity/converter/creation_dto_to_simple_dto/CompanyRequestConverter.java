package com.start.stockdata.identity.converter.creation_dto_to_simple_dto;

import com.start.stockdata.identity.dto.request.CompanyRequestDto;
import com.start.stockdata.identity.dto.response.CompanyDto;
import com.start.stockdata.identity.dto.response.CompanyTypeDto;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@Component
public class CompanyRequestConverter implements RequestConverter<CompanyRequestDto, CompanyDto> {

    private final CompanyFieldRequestConverter companyFieldRequestConverter;
    private final CompanyFactoryRequestConverter companyFactoryRequestConverter;

    public CompanyRequestConverter(CompanyFieldRequestConverter companyFieldRequestConverter,
                                   CompanyFactoryRequestConverter companyFactoryRequestConverter) {
        this.companyFieldRequestConverter = companyFieldRequestConverter;
        this.companyFactoryRequestConverter = companyFactoryRequestConverter;
    }

    @Override
    public CompanyDto convert(CompanyRequestDto creationDto) {
        return ofNullable(creationDto)
                .map(item -> {
                    CompanyDto companyDto = new CompanyDto();
                    companyDto.setName(item.getName());
                    companyDto.setTypes(item.getTypes());
                    companyDto.setFields(item
                            .getFields()
                            .stream()
                            .map(companyFieldRequestConverter::convert)
                            .collect(Collectors.toSet()));
                    companyDto.setFactors(item
                            .getFactors()
                            .stream()
                            .map(companyFactoryRequestConverter::convert)
                            .collect(Collectors.toSet()));
                    return companyDto;
                })
                .orElse(null);
    }

}

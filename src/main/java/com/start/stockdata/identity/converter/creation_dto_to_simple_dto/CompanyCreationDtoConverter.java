package com.start.stockdata.identity.converter.creation_dto_to_simple_dto;

import com.start.stockdata.identity.dto.CompanyCreationDto;
import com.start.stockdata.identity.dto.CompanyDto;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@Component
public class CompanyCreationDtoConverter implements CreationDtoToSimpleDtoConverter<CompanyCreationDto, CompanyDto> {

    private final CompanyTypeCreationDtoConverter companyTypeCreationDtoConverter;
    private final CompanyFieldCreationDtoConverter companyFieldCreationDtoConverter;
    private final CompanyFactoryCreationDtoConverter companyFactoryCreationDtoConverter;

    public CompanyCreationDtoConverter(CompanyTypeCreationDtoConverter companyTypeCreationDtoConverter,
                                       CompanyFieldCreationDtoConverter companyFieldCreationDtoConverter,
                                       CompanyFactoryCreationDtoConverter companyFactoryCreationDtoConverter) {
        this.companyTypeCreationDtoConverter = companyTypeCreationDtoConverter;
        this.companyFieldCreationDtoConverter = companyFieldCreationDtoConverter;
        this.companyFactoryCreationDtoConverter = companyFactoryCreationDtoConverter;
    }

    @Override
    public CompanyDto convert(CompanyCreationDto creationDto) {
        return ofNullable(creationDto)
                .map(item -> {
                    CompanyDto companyDto = new CompanyDto();
                    companyDto.setName(item.getName());
                    companyDto.setTypes(item
                            .getTypes()
                            .stream()
                            .map(companyTypeCreationDtoConverter::convert)
                            .collect(Collectors.toSet()));
                    companyDto.setFields(item
                            .getFields()
                            .stream()
                            .map(companyFieldCreationDtoConverter::convert)
                            .collect(Collectors.toSet()));
                    companyDto.setFactors(item
                            .getFactors()
                            .stream()
                            .map(companyFactoryCreationDtoConverter::convert)
                            .collect(Collectors.toSet()));
                    return companyDto;
                })
                .orElse(null);
    }

}

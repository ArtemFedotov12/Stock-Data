package com.start.stockdata.identity.converter.creation_dto_to_simple_dto;

import com.start.stockdata.identity.dto.request.CompanyRequestDto;
import com.start.stockdata.identity.dto.response.CompanyResponseDto;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@Component
public class CompanyDeprecatedRequestConverter implements DeprecatedRequestConverter<CompanyRequestDto, CompanyResponseDto> {

    private final CompanyFieldDeprecatedRequestConverter companyFieldRequestConverter;
    private final CompanyFactoryDeprecatedRequestConverter companyFactoryRequestConverter;

    public CompanyDeprecatedRequestConverter(CompanyFieldDeprecatedRequestConverter companyFieldRequestConverter,
                                             CompanyFactoryDeprecatedRequestConverter companyFactoryRequestConverter) {
        this.companyFieldRequestConverter = companyFieldRequestConverter;
        this.companyFactoryRequestConverter = companyFactoryRequestConverter;
    }

    @Override
    public CompanyResponseDto convert(CompanyRequestDto creationDto) {
        return ofNullable(creationDto)
                .map(item -> {
                    CompanyResponseDto companyResponseDto = new CompanyResponseDto();
                    companyResponseDto.setName(item.getName());
                    companyResponseDto.setTypes(item.getTypes());
                    companyResponseDto.setFields(item
                            .getFields()
                            .stream()
                            .map(companyFieldRequestConverter::convert)
                            .collect(Collectors.toSet()));
                    companyResponseDto.setFactors(item
                            .getFactors()
                            .stream()
                            .map(companyFactoryRequestConverter::convert)
                            .collect(Collectors.toSet()));
                    return companyResponseDto;
                })
                .orElse(null);
    }

}

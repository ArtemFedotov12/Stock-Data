package com.start.stockdata.identity.converter.response;

import com.start.stockdata.identity.dto.response.CompanyResponseDto;
import com.start.stockdata.identity.model.Company;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@Component
public class CompanyResponseConverter implements ResponseConverter<Company, CompanyResponseDto> {

    private final FieldResponseConverter fieldResponseConverter;
    private final FactorResponseConverter factorResponseConverter;
    private final CompanyTypeResponseConverter companyTypeResponseConverter;

    public CompanyResponseConverter(FieldResponseConverter fieldResponseConverter, FactorResponseConverter factorResponseConverter, CompanyTypeResponseConverter companyTypeResponseConverter) {
        this.fieldResponseConverter = fieldResponseConverter;
        this.factorResponseConverter = factorResponseConverter;
        this.companyTypeResponseConverter = companyTypeResponseConverter;
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
                            .map(companyTypeResponseConverter::toDto)
                            .collect(Collectors.toSet()));
                    companyResponseDto.setFields(item
                            .getFields()
                            .stream()
                            .map(fieldResponseConverter::toDto)
                            .collect(Collectors.toSet()));
                    companyResponseDto.setFactors(item
                            .getFactors()
                            .stream()
                            .map(factorResponseConverter::toDto)
                            .collect(Collectors.toSet()));
                    companyResponseDto.setUserId(item.getUserId());
                    return companyResponseDto;
                })
                .orElse(null);
    }

}

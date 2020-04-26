package com.start.stockdata.identity.converter.response;

import com.start.stockdata.identity.dto.response.CompanyFieldResponseDto;
import com.start.stockdata.identity.model.CompanyField;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
public class CompanyFieldConverter implements ResponseConverter<CompanyField, CompanyFieldResponseDto> {

    @Override
    public CompanyField toEntity(CompanyFieldResponseDto dto) {
        return ofNullable(dto)
                .map(item -> {
                   CompanyField companyField = new CompanyField();
                   companyField.setId(item.getId());
                   companyField.setShortName(item.getDisplayName());
                   companyField.setDisplayName(item.getDisplayName());
                   companyField.setAsset(item.getAsset());
                    return companyField;
                })
                .orElse(null);
    }


    @Override
    public CompanyFieldResponseDto toDto(CompanyField entity) {
        return ofNullable(entity)
                .map(item -> {
                 CompanyFieldResponseDto companyFieldResponseDto = new CompanyFieldResponseDto();
                 companyFieldResponseDto.setId(item.getId());
                 companyFieldResponseDto.setDisplayName(item.getDisplayName());
                 companyFieldResponseDto.setAsset(item.getAsset());
                    return companyFieldResponseDto;
                })
                .orElse(null);
    }
}

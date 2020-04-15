package com.start.stockdata.identity.converter.entity_to_dto;

import com.start.stockdata.identity.dto.response.CompanyFieldDto;
import com.start.stockdata.identity.model.CompanyField;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
public class CompanyFieldConverter implements EntityDtoConverter<CompanyField, CompanyFieldDto> {

    @Override
    public CompanyField toEntity(CompanyFieldDto dto) {
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
    public CompanyFieldDto toDto(CompanyField entity) {
        return ofNullable(entity)
                .map(item -> {
                 CompanyFieldDto companyFieldDto = new CompanyFieldDto();
                 companyFieldDto.setId(item.getId());
                 companyFieldDto.setDisplayName(item.getDisplayName());
                 companyFieldDto.setAsset(item.getAsset());
                    return companyFieldDto;
                })
                .orElse(null);
    }
}

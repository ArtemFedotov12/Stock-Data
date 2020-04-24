package com.start.stockdata.identity.converter.entity_to_dto;

import com.start.stockdata.identity.dto.response.CompanyFactorResponseDto;
import com.start.stockdata.identity.model.CompanyFactor;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
public class CompanyFactorConverter implements ResponseConverter<CompanyFactor, CompanyFactorResponseDto> {

    @Override
    public CompanyFactor toEntity(CompanyFactorResponseDto dto) {
        return ofNullable(dto)
                .map(item -> {
                    CompanyFactor companyFactor = new CompanyFactor();
                    companyFactor.setId(item.getId());
                    companyFactor.setShortName(item.getDisplayName());
                    companyFactor.setDisplayName(item.getDisplayName());
                    companyFactor.setAsset(item.getAsset());
                    return companyFactor;
                })
                .orElse(null);
    }

    @Override
    public CompanyFactorResponseDto toDto(CompanyFactor entity) {
        return ofNullable(entity)
                .map(item -> {
                    CompanyFactorResponseDto companyFactorResponseDto = new CompanyFactorResponseDto();
                    companyFactorResponseDto.setId(item.getId());
                    companyFactorResponseDto.setDisplayName(item.getDisplayName());
                    companyFactorResponseDto.setAsset(item.getAsset());
                    return companyFactorResponseDto;
                })
                .orElse(null);
    }

}

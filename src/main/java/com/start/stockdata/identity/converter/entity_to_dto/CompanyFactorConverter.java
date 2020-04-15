package com.start.stockdata.identity.converter.entity_to_dto;

import com.start.stockdata.identity.dto.response.CompanyFactorDto;
import com.start.stockdata.identity.model.CompanyFactor;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
public class CompanyFactorConverter implements EntityDtoConverter<CompanyFactor, CompanyFactorDto> {

    @Override
    public CompanyFactor toEntity(CompanyFactorDto dto) {
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
    public CompanyFactorDto toDto(CompanyFactor entity) {
        return ofNullable(entity)
                .map(item -> {
                    CompanyFactorDto companyFactorDto = new CompanyFactorDto();
                    companyFactorDto.setId(item.getId());
                    companyFactorDto.setDisplayName(item.getDisplayName());
                    companyFactorDto.setAsset(item.getAsset());
                    return companyFactorDto;
                })
                .orElse(null);
    }

}

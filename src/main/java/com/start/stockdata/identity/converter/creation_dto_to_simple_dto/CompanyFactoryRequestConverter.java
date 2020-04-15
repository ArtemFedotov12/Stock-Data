package com.start.stockdata.identity.converter.creation_dto_to_simple_dto;

import com.start.stockdata.identity.dto.request.CompanyFactorRequestDto;
import com.start.stockdata.identity.dto.response.CompanyFactorDto;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
public class CompanyFactoryRequestConverter implements RequestConverter<CompanyFactorRequestDto, CompanyFactorDto> {

    @Override
    public CompanyFactorDto convert(CompanyFactorRequestDto creationDto) {
        return ofNullable(creationDto)
                .map(item -> {
                    CompanyFactorDto companyFactorDto = new CompanyFactorDto();
                    companyFactorDto.setDisplayName(item.getDisplayName());
                    companyFactorDto.setAsset(item.getAsset());
                  return companyFactorDto;
                })
                .orElse(null);
    }
}

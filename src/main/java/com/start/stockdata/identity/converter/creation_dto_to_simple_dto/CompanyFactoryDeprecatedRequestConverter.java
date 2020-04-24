package com.start.stockdata.identity.converter.creation_dto_to_simple_dto;

import com.start.stockdata.identity.dto.request.CompanyFactorRequestDto;
import com.start.stockdata.identity.dto.response.CompanyFactorResponseDto;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
public class CompanyFactoryDeprecatedRequestConverter implements DeprecatedRequestConverter<CompanyFactorRequestDto, CompanyFactorResponseDto> {

    @Override
    public CompanyFactorResponseDto convert(CompanyFactorRequestDto creationDto) {
        return ofNullable(creationDto)
                .map(item -> {
                    CompanyFactorResponseDto companyFactorResponseDto = new CompanyFactorResponseDto();
                    companyFactorResponseDto.setDisplayName(item.getDisplayName());
                    companyFactorResponseDto.setAsset(item.getAsset());
                  return companyFactorResponseDto;
                })
                .orElse(null);
    }
}

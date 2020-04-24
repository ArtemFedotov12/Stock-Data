package com.start.stockdata.identity.converter.creation_dto_to_simple_dto;

import com.start.stockdata.identity.dto.request.CompanyFieldRequestDto;
import com.start.stockdata.identity.dto.response.CompanyFieldResponseDto;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
public class CompanyFieldDeprecatedRequestConverter implements DeprecatedRequestConverter<CompanyFieldRequestDto, CompanyFieldResponseDto> {

    @Override
    public CompanyFieldResponseDto convert(CompanyFieldRequestDto creationDto) {
        return ofNullable(creationDto)
                .map(item -> {
                    CompanyFieldResponseDto companyFieldResponseDto = new CompanyFieldResponseDto();
                    companyFieldResponseDto.setDisplayName(item.getDisplayName());
                    companyFieldResponseDto.setAsset(item.getAsset());
                    return companyFieldResponseDto;
                })
                .orElse(null);
    }

}

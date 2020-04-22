package com.start.stockdata.identity.converter.creation_dto_to_simple_dto;

import com.start.stockdata.identity.dto.request.CompanyTypeRequestDto;
import com.start.stockdata.identity.dto.response.CompanyTypeResponseDto;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
public class CompanyTypeDeprecatedRequestConverter implements DeprecatedRequestConverter<CompanyTypeRequestDto, CompanyTypeResponseDto> {

    @Override
    public CompanyTypeResponseDto convert(CompanyTypeRequestDto creationDto) {
        return ofNullable(creationDto)
                .map(item -> {
                    CompanyTypeResponseDto companyTypeResponseDto = new CompanyTypeResponseDto();
                    companyTypeResponseDto.setType(item.getType());
                    return companyTypeResponseDto;
                })
                .orElse(null);
    }

}

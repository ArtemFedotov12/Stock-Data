package com.start.stockdata.identity.converter.creation_dto_to_simple_dto;

import com.start.stockdata.identity.dto.request.CompanyTypeRequestDto;
import com.start.stockdata.identity.dto.response.CompanyTypeDto;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
public class CompanyTypeRequestConverter implements RequestConverter<CompanyTypeRequestDto, CompanyTypeDto> {

    @Override
    public CompanyTypeDto convert(CompanyTypeRequestDto creationDto) {
        return ofNullable(creationDto)
                .map(item -> {
                    CompanyTypeDto companyTypeDto = new CompanyTypeDto();
                    companyTypeDto.setType(item.getType());
                    return companyTypeDto;
                })
                .orElse(null);
    }

}

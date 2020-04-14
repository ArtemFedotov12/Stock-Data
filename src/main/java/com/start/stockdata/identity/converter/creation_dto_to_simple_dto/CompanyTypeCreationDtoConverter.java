package com.start.stockdata.identity.converter.creation_dto_to_simple_dto;

import com.start.stockdata.identity.dto.CompanyTypeCreationDto;
import com.start.stockdata.identity.dto.CompanyTypeDto;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
public class CompanyTypeCreationDtoConverter implements CreationDtoToSimpleDtoConverter<CompanyTypeCreationDto, CompanyTypeDto> {

    @Override
    public CompanyTypeDto convert(CompanyTypeCreationDto creationDto) {
        return ofNullable(creationDto)
                .map(item -> {
                    CompanyTypeDto companyTypeDto = new CompanyTypeDto();
                    companyTypeDto.setType(item.getType());
                    return companyTypeDto;
                })
                .orElse(null);
    }

}

package com.start.stockdata.identity.converter.creation_dto_to_simple_dto;

import com.start.stockdata.identity.dto.CompanyFieldCreationDto;
import com.start.stockdata.identity.dto.CompanyFieldDto;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
public class CompanyFieldCreationDtoConverter implements CreationDtoToSimpleDtoConverter<CompanyFieldCreationDto, CompanyFieldDto> {

    @Override
    public CompanyFieldDto convert(CompanyFieldCreationDto creationDto) {
        return ofNullable(creationDto)
                .map(item -> {
                    CompanyFieldDto companyFieldDto = new CompanyFieldDto();
                    companyFieldDto.setDisplayName(item.getDisplayName());
                    companyFieldDto.setAsset(item.getAsset());
                    return companyFieldDto;
                })
                .orElse(null);
    }

}

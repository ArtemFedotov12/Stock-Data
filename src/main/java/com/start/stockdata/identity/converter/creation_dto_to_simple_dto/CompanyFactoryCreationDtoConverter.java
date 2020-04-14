package com.start.stockdata.identity.converter.creation_dto_to_simple_dto;

import com.start.stockdata.identity.dto.CompanyFactorCreationDto;
import com.start.stockdata.identity.dto.CompanyFactorDto;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
public class CompanyFactoryCreationDtoConverter implements CreationDtoToSimpleDtoConverter<CompanyFactorCreationDto, CompanyFactorDto> {

    @Override
    public CompanyFactorDto convert(CompanyFactorCreationDto creationDto) {
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

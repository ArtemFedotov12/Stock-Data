package com.start.stockdata.identity.converter.entity_to_dto;

import com.start.stockdata.identity.dto.CompanyTypeDto;
import com.start.stockdata.identity.model.CompanyType;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
public class CompanyTypeConverter implements EntityDtoConverter<CompanyType, CompanyTypeDto> {

    @Override
    public CompanyType toEntity(CompanyTypeDto dto) {
        return ofNullable(dto)
                .map(item -> {
                    CompanyType companyType = new CompanyType();
                    companyType.setId(item.getId());
                    companyType.setType(item.getType());
                    return companyType;
                })
                .orElse(null);
    }

    @Override
    public CompanyTypeDto toDto(CompanyType entity) {
        return ofNullable(entity)
                .map(item -> {
                    CompanyTypeDto companyTypeDto = new CompanyTypeDto();
                    companyTypeDto.setId(item.getId());
                    companyTypeDto.setType(item.getType());
                    return companyTypeDto;
                })
                .orElse(null);
    }

}

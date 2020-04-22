package com.start.stockdata.identity.converter.entity_to_dto;

import com.start.stockdata.identity.dto.response.CompanyTypeResponseDto;
import com.start.stockdata.identity.model.CompanyType;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
public class CompanyTypeConverter implements ResponseConverter<CompanyType, CompanyTypeResponseDto> {

    @Override
    public CompanyType toEntity(CompanyTypeResponseDto dto) {
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
    public CompanyTypeResponseDto toDto(CompanyType entity) {
        return ofNullable(entity)
                .map(item -> {
                    CompanyTypeResponseDto companyTypeResponseDto = new CompanyTypeResponseDto();
                    companyTypeResponseDto.setId(item.getId());
                    companyTypeResponseDto.setType(item.getType());
                    return companyTypeResponseDto;
                })
                .orElse(null);
    }

}

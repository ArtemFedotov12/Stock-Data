package com.start.stockdata.identity.converter.response;

import com.start.stockdata.identity.dto.response.CompanyTypeResponseDto;
import com.start.stockdata.identity.model.CompanyType;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
public class CompanyTypeResponseConverter implements ResponseConverter<CompanyType, CompanyTypeResponseDto> {


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

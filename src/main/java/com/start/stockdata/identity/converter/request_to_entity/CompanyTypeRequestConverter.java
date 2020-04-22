package com.start.stockdata.identity.converter.request_to_entity;

import com.start.stockdata.identity.dto.request.CompanyTypeRequestDto;
import com.start.stockdata.identity.model.CompanyType;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
public class CompanyTypeRequestConverter implements RequestConverter<CompanyType, CompanyTypeRequestDto> {

    @Override
    public CompanyType toEntity(CompanyTypeRequestDto requestDto) {
        return ofNullable(requestDto)
                .map(item -> {
                    CompanyType companyType = new CompanyType();
                    companyType.setType(item.getType());
                    return companyType;
                })
                .orElse(null);
    }

}

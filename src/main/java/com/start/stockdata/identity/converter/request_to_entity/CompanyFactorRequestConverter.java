package com.start.stockdata.identity.converter.request_to_entity;

import com.start.stockdata.identity.dto.request.CompanyFactorRequestDto;
import com.start.stockdata.identity.model.CompanyFactor;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
public class CompanyFactorRequestConverter implements RequestConverter<CompanyFactor, CompanyFactorRequestDto> {

    @Override
    public CompanyFactor toEntity(CompanyFactorRequestDto requestDto) {
        return ofNullable(requestDto)
                .map(item -> {
                    CompanyFactor companyFactor =new CompanyFactor();
                    companyFactor.setAsset(item.getAsset());
                    companyFactor.setDisplayName(item.getDisplayName());
                    return companyFactor;
                })
                .orElse(null);
    }
}

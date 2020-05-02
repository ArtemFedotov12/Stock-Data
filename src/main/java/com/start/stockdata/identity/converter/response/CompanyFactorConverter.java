package com.start.stockdata.identity.converter.response;

import com.start.stockdata.identity.dto.response.CompanyFactorResponseDto;
import com.start.stockdata.identity.model.Factor;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
public class CompanyFactorConverter implements ResponseConverter<Factor, CompanyFactorResponseDto> {

    @Override
    public Factor toEntity(CompanyFactorResponseDto dto) {
        return ofNullable(dto)
                .map(item -> {
                    Factor factor = new Factor();
                    factor.setId(item.getId());
                    factor.setShortName(item.getDisplayName());
                    factor.setDisplayName(item.getDisplayName());
                    factor.setAsset(item.getAsset());
                    return factor;
                })
                .orElse(null);
    }

    @Override
    public CompanyFactorResponseDto toDto(Factor entity) {
        return ofNullable(entity)
                .map(item -> {
                    CompanyFactorResponseDto companyFactorResponseDto = new CompanyFactorResponseDto();
                    companyFactorResponseDto.setId(item.getId());
                    companyFactorResponseDto.setDisplayName(item.getDisplayName());
                    companyFactorResponseDto.setAsset(item.getAsset());
                    return companyFactorResponseDto;
                })
                .orElse(null);
    }

}

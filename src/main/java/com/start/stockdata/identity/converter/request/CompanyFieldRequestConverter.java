package com.start.stockdata.identity.converter.request;

import com.start.stockdata.identity.dto.request.CompanyFieldRequestDto;
import com.start.stockdata.identity.model.CompanyField;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
public class CompanyFieldRequestConverter implements RequestConverter<CompanyField, CompanyFieldRequestDto> {

    @Override
    public CompanyField toEntity(CompanyFieldRequestDto requestDto) {
        return ofNullable(requestDto)
                .map(item -> {
                    CompanyField companyField = new CompanyField();
                    companyField.setAsset(item.getAsset());
                    companyField.setDisplayName(item.getAsset());
                    return companyField;
                })
                .orElse(null);
    }
}



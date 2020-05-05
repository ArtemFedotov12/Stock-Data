package com.start.stockdata.identity.converter.request;

import com.start.stockdata.identity.dto.request.FactorRequestDto;
import com.start.stockdata.identity.model.Factor;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
public class CompanyFactorRequestConverter implements RequestConverter<Factor, FactorRequestDto> {

    @Override
    public Factor toEntity(FactorRequestDto requestDto) {
        return ofNullable(requestDto)
                .map(item -> {
                    Factor factor =new Factor();
                    factor.setAsset(item.getAsset());
                    factor.setDisplayName(item.getDisplayName());
                    return factor;
                })
                .orElse(null);
    }
}

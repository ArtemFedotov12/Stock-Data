package com.start.stockdata.identity.converter.request;

import com.start.stockdata.identity.dto.request.FactorRequestDto;
import com.start.stockdata.identity.model.Factor;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
public class FactorRequestConverter implements RequestConverter<Factor, FactorRequestDto> {

    @Override
    public Factor toEntity(FactorRequestDto requestDto) {
        return ofNullable(requestDto)
                .map(item -> {
                    Factor factor =new Factor();
                    factor.setAsset(item.getAsset());
                    factor.setDisplayName(item.getDisplayName());
                    factor.setShortName(convert(item.getDisplayName()));
                    return factor;
                })
                .orElse(null);
    }

    private String convert(String userLine) {
        StringBuilder result = new StringBuilder();
        String[] arr = userLine.trim().replaceAll(" +", " ").split(" ");
        result.append(arr[0].toLowerCase());
        for (int i = 1; i < arr.length-1; i++) {
            result.append(Character.toString(arr[i].charAt(0)).toUpperCase());
            if(arr[i].length()>1) {
                result.append(arr[i].substring(1).toLowerCase());
            }
        }

        return result.toString();
    }

}

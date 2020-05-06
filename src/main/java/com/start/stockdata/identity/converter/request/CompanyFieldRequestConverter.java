package com.start.stockdata.identity.converter.request;

import com.start.stockdata.identity.dto.request.FieldRequestDto;
import com.start.stockdata.identity.model.Field;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
public class CompanyFieldRequestConverter implements RequestConverter<Field, FieldRequestDto> {

    @Override
    public Field toEntity(FieldRequestDto requestDto) {
        return ofNullable(requestDto)
                .map(item -> {
                    Field field = new Field();
                    field.setAsset(item.getAsset());
                    field.setDisplayName(item.getDisplayName());
                    field.setShortName(convert(item.getDisplayName()));
                    return field;
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



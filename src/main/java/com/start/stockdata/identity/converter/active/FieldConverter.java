package com.start.stockdata.identity.converter.active;

import com.start.stockdata.identity.dto.request.FieldRequestDto;
import com.start.stockdata.identity.dto.response.FieldResponseDto;
import com.start.stockdata.identity.model.Field;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
public class FieldConverter implements Converter<
        Field,
        FieldRequestDto,
        FieldResponseDto
        > {



    @Override
    public Field toEntity(FieldRequestDto requestDto) {
        return ofNullable(requestDto)
                .map(item -> {
                    Field companyField = new Field();
                    companyField.setDisplayName(item.getDisplayName());
                    companyField.setAsset(item.getAsset());
                    companyField.setShortName(convert(item.getDisplayName()));
                    return companyField;
                })
                .orElse(null);
    }

    @Override
    public FieldResponseDto toDto(Field entity) {
        return ofNullable(entity)
                .map(item -> {
                    FieldResponseDto responseDto = new FieldResponseDto();
                    responseDto.setId(item.getId());
                    responseDto.setDisplayName(item.getDisplayName());
                    responseDto.setAsset(item.getAsset());
                    return responseDto;
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

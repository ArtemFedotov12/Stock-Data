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
                    return field;
                })
                .orElse(null);
    }

}



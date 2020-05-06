package com.start.stockdata.identity.converter.response;

import com.start.stockdata.identity.dto.response.FieldResponseDto;
import com.start.stockdata.identity.model.Field;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
public class FieldResponseConverter implements ResponseConverter<Field, FieldResponseDto> {

    @Override
    public FieldResponseDto toDto(Field entity) {
        return ofNullable(entity)
                .map(item -> {
                 FieldResponseDto fieldResponseDto = new FieldResponseDto();
                 fieldResponseDto.setId(item.getId());
                 fieldResponseDto.setDisplayName(item.getDisplayName());
                 fieldResponseDto.setAsset(item.getAsset());
                    return fieldResponseDto;
                })
                .orElse(null);
    }
}

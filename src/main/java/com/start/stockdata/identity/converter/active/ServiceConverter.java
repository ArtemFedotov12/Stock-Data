package com.start.stockdata.identity.converter.active;

import com.start.stockdata.identity.dto.request.AbstractRequestDto;
import com.start.stockdata.identity.dto.response.AbstractResponseDto;
import com.start.stockdata.identity.model.AbstractEntity;

public interface ServiceConverter<
        E extends AbstractEntity,
        RQ extends AbstractRequestDto,
        RS extends AbstractResponseDto
        > {

    E toEntity(RQ requestDto);
    RS toDto(E entity);
}

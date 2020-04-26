package com.start.stockdata.identity.converter.active;

import com.start.stockdata.identity.dto.active.AbstractActiveDto;
import com.start.stockdata.identity.dto.request.AbstractRequestDto;
import com.start.stockdata.identity.dto.response.AbstractResponseDto;
import com.start.stockdata.identity.model.AbstractEntity;

public interface ServiceConverter<
        A extends AbstractActiveDto,
        E extends AbstractEntity,
        RQ extends AbstractRequestDto,
        RS extends AbstractResponseDto
        > {

    A toActive(RQ requestDto);
    E toEntity(A activeDto);
    E toEntity(RQ requestDto);
    RS toDto(E entity);
}

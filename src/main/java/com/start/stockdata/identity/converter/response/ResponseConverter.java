package com.start.stockdata.identity.converter.response;

import com.start.stockdata.identity.dto.response.AbstractResponseDto;
import com.start.stockdata.identity.model.AbstractEntity;

public interface ResponseConverter<E extends AbstractEntity, RS extends AbstractResponseDto> {

    RS toDto(E entity);

}

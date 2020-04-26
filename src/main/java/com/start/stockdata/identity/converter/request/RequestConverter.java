package com.start.stockdata.identity.converter.request;

import com.start.stockdata.identity.dto.request.AbstractRequestDto;
import com.start.stockdata.identity.model.AbstractEntity;

public interface RequestConverter<E extends AbstractEntity, RQ extends AbstractRequestDto> {

    E toEntity(RQ requestDto);

}

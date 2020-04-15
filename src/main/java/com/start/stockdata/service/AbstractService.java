package com.start.stockdata.service;

import com.start.stockdata.identity.converter.creation_dto_to_simple_dto.RequestConverter;
import com.start.stockdata.identity.dto.request.AbstractRequestDto;
import com.start.stockdata.identity.dto.response.AbstractResponseDto;
import com.start.stockdata.wrapper.AbstractEntityDtoWrapper;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class AbstractService<
        RQ extends AbstractRequestDto,
        RS extends AbstractResponseDto,
        WR extends AbstractEntityDtoWrapper<?, RS, ?>> {

    protected final WR wrapper;
    protected final RequestConverter<RQ, RS> converter;

    public AbstractService(WR wrapper, RequestConverter<RQ, RS> converter) {
        this.wrapper = wrapper;
        this.converter = converter;
    }

    public RS save(RQ creationDto) {
        RS simpleDto = converter.convert(creationDto);
        return wrapper.save(simpleDto);
    }

}

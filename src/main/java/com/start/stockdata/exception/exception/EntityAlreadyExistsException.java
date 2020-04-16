package com.start.stockdata.exception.exception;

import com.start.stockdata.exception.StockExceptionType;
import com.start.stockdata.identity.dto.request.AbstractRequestDto;

public class EntityAlreadyExistsException extends StockException {

    private static final long serialVersionUID = -2243406705050894179L;
    private static final String DEFAULT_MESSAGE = "Such entity already exists";
    private final AbstractRequestDto dto;

    public EntityAlreadyExistsException(AbstractRequestDto dto) {
        super(String.format(DEFAULT_MESSAGE, dto.toString()));
        this.dto = dto;
    }

    @Override
    public StockExceptionType getType() {
        return StockExceptionType.ENTITY_ALREADY_EXISTS;
    }

    @Override
    public Object[] getArguments() {
        return new Object[]{dto.toString()};
    }

}

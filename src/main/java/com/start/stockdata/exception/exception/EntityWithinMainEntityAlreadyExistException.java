package com.start.stockdata.exception.exception;

import com.start.stockdata.exception.StockExceptionType;
import com.start.stockdata.identity.dto.request.AbstractRequestDto;

public class EntityWithinMainEntityAlreadyExistException extends StockException {

    private static final long serialVersionUID = -6594188722529556676L;
    private static final String DEFAULT_MESSAGE = "Such entity already exists within the main entity with id=%s";
    private final AbstractRequestDto dto;

    public EntityWithinMainEntityAlreadyExistException(String mainEntityId,AbstractRequestDto dto) {
        super(String.format(DEFAULT_MESSAGE, mainEntityId));
        this.dto = dto;
    }

    @Override
    public StockExceptionType getType() {
        return StockExceptionType.ENTITY_ALREADY_EXISTS_WITHIN_THE_MAIN_ENTITY;
    }

    @Override
    public Object[] getArguments() {
        return new Object[]{dto};
    }

}

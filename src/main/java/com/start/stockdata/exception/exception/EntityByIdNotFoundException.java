package com.start.stockdata.exception.exception;

import com.start.stockdata.exception.StockExceptionType;

public class EntityByIdNotFoundException extends StockException {

    private static final String DEFAULT_MESSAGE = "Entity with id=%d doesn't exist";
    private static final long serialVersionUID = -4920264305760157619L;

    public EntityByIdNotFoundException(Long entityId) {
        super(String.format(DEFAULT_MESSAGE, entityId));
    }

    @Override
    public StockExceptionType getType() {
        return StockExceptionType.USER_BY_ID_NOT_FOUND;
    }

}

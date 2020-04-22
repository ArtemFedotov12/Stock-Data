package com.start.stockdata.exception.exception;

import com.start.stockdata.exception.StockExceptionType;

public class EntityByIdNotFoundException extends StockException {

    private static final String DEFAULT_MESSAGE = "User with such id doesn't exist";
    private static final long serialVersionUID = -4920264305760157619L;

    public EntityByIdNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    @Override
    public StockExceptionType getType() {
        return StockExceptionType.USER_BY_ID_NOT_FOUND;
    }

}

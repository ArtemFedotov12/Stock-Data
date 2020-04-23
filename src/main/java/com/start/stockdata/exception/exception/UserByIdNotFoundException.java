package com.start.stockdata.exception.exception;

import com.start.stockdata.exception.StockExceptionType;

public class UserByIdNotFoundException extends StockException {

    private static final String DEFAULT_MESSAGE = "User id hasn't been found in Security Context";
    private static final long serialVersionUID = -4920264305760157619L;

    public UserByIdNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    @Override
    public StockExceptionType getType() {
        return StockExceptionType.ENTITY_BY_ID_NOT_FOUND;
    }

}

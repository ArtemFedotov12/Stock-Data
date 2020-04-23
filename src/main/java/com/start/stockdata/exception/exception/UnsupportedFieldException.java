package com.start.stockdata.exception.exception;

import com.start.stockdata.exception.StockExceptionType;

public class UnsupportedFieldException extends StockException {

    private static final String DEFAULT_MESSAGE = "Field '%s' not supported";
    private static final long serialVersionUID = -4920264305760157619L;

    public UnsupportedFieldException(String fieldName) {
        super(String.format(DEFAULT_MESSAGE, fieldName));
    }

    @Override
    public StockExceptionType getType() {
        return StockExceptionType.ENTITY_BY_ID_NOT_FOUND;
    }

}

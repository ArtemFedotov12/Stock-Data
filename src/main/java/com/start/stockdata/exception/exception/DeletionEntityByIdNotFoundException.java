package com.start.stockdata.exception.exception;

import com.start.stockdata.exception.StockExceptionType;

public class DeletionEntityByIdNotFoundException extends StockException {

    private static final long serialVersionUID = 143735860890717060L;
    private static final String DEFAULT_MESSAGE = "Entity with such id doesn't exist";

    public DeletionEntityByIdNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    @Override
    public StockExceptionType getType() {
        return StockExceptionType.ENTITY_WITH_SUCH_ID_NOT_FOUND;
    }

}

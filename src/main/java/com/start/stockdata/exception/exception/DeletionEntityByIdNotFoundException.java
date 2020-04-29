package com.start.stockdata.exception.exception;

import com.start.stockdata.exception.StockExceptionType;

public class DeletionEntityByIdNotFoundException extends StockException {

    private static final long serialVersionUID = 143735860890717060L;
    private static final String DEFAULT_MESSAGE = "Entity with such id=%s doesn't exist";

    public DeletionEntityByIdNotFoundException(String id) {
        super(String.format(DEFAULT_MESSAGE,id));
    }

    @Override
    public StockExceptionType getType() {
        return StockExceptionType.ENTITY_WITH_SUCH_ID_NOT_FOUND;
    }

}

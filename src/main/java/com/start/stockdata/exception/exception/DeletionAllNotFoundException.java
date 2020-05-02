package com.start.stockdata.exception.exception;

import com.start.stockdata.exception.StockExceptionType;

public class DeletionAllNotFoundException extends StockException {


    private static final long serialVersionUID = -1596063376503916595L;
    private static final String DEFAULT_MESSAGE = "No entities in database, nothing to be deleted";

    public DeletionAllNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    @Override
    public StockExceptionType getType() {
        return StockExceptionType.DELETE_ALL_NOT_FOUND_ENTITY;
    }

}

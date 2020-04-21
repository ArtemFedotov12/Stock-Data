package com.start.stockdata.exception.exception;

import com.start.stockdata.exception.StockExceptionType;

public class UndefinedException extends StockException {

    private static final long serialVersionUID = -495400688164886246L;



    public UndefinedException(Throwable ex) {
        super(ex);
    }

    @Override
    public StockExceptionType getType() {
        return StockExceptionType.UNDEFINED_EXCEPTION;
    }

}

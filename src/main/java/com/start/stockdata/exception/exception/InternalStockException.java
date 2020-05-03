package com.start.stockdata.exception.exception;

import com.start.stockdata.exception.StockExceptionType;

public class InternalStockException extends StockException {

    private static final long serialVersionUID = 108055769217226309L;

    public InternalStockException(String message) {
        super(message);
    }

    @Override
    public StockExceptionType getType() {
        return StockExceptionType.INTERNAL_STOCK_EXCEPTION;
    }

}

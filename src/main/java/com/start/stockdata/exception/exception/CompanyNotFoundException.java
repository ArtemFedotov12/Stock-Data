package com.start.stockdata.exception.exception;

import com.start.stockdata.exception.StockExceptionType;

public class CompanyNotFoundException extends StockException{

    private static final long serialVersionUID = 640427192975090258L;
    private static final String DEFAULT_MESSAGE = "User doesn't have any companies";

    public CompanyNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    @Override
    public StockExceptionType getType() {
        return StockExceptionType.COMPANY_NOT_EXISTS;
    }

}

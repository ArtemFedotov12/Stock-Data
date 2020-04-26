package com.start.stockdata.exception.exception;

import com.start.stockdata.exception.StockExceptionType;

public class CompanyByIdNotFoundException extends StockException {

    private static final long serialVersionUID = 1076386918376019594L;
    private static final String DEFAULT_MESSAGE = "Company with id=%d doesn't exist";


    public CompanyByIdNotFoundException(Long companyId) {
        super(String.format(DEFAULT_MESSAGE, companyId));
    }

    @Override
    public StockExceptionType getType() {
        return StockExceptionType.ENTITY_BY_ID_NOT_FOUND;
    }
}

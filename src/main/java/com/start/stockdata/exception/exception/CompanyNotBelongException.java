package com.start.stockdata.exception.exception;

import com.start.stockdata.exception.StockExceptionType;

public class CompanyNotBelongException extends StockException {

    private static final String DEFAULT_MESSAGE = "Company with id=%s doesn't belong to current user ";
    private static final long serialVersionUID = -4920264305760157619L;

    public CompanyNotBelongException(Long companyId) {
        super(String.format(DEFAULT_MESSAGE, companyId));
    }

    @Override
    public StockExceptionType getType() {
        return StockExceptionType.COMPANY_NOT_BELONG;
    }

}

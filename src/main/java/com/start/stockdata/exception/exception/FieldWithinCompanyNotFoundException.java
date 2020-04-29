package com.start.stockdata.exception.exception;

import com.start.stockdata.exception.StockExceptionType;

public class FieldWithinCompanyNotFoundException extends StockException {


    private static final long serialVersionUID = 6701986751692406006L;
    private static final String DEFAULT_MESSAGE = "Field with id=%s doesn't exist within the company with id=%s";

    public FieldWithinCompanyNotFoundException(String companyId, String fieldId) {
        super(String.format(DEFAULT_MESSAGE, fieldId, companyId));
    }

    @Override
    public StockExceptionType getType() {
        return StockExceptionType.FIELD_DOES_NOT_EXIST_WITHIN_COMPANY;
    }
}

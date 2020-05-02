package com.start.stockdata.exception.exception;

import com.start.stockdata.exception.StockExceptionType;

public class SaveFieldException extends StockException {


    private static final long serialVersionUID = 5763786918973648426L;
    private static final String DEFAULT_MESSAGE = "Error occurs while saving company's field";

    public SaveFieldException() {
        super(DEFAULT_MESSAGE);
    }

    @Override
    public StockExceptionType getType() {
        return StockExceptionType.SAVE_FIELD;
    }

}

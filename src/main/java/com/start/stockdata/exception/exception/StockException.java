package com.start.stockdata.exception.exception;

import com.start.stockdata.exception.StockExceptionType;
import org.springframework.http.HttpStatus;

public abstract class StockException extends RuntimeException {

    private static final long serialVersionUID = 782506404968583262L;

    public StockException(String message) {
        super(message);
    }

    public StockException(Throwable cause) {
        super(cause);
    }

    public StockException(String message, Throwable cause) {
        super(message, cause);
    }

    public abstract StockExceptionType getType();

    public String getCode() {
        return getType().getCode();
    }

    public HttpStatus getStatus() {
        return getType().getStatus();
    }

    public Object[] getArguments() {
        return null;
    }
    
}

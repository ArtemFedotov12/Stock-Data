package com.start.stockdata.exception.exception;

import com.start.stockdata.exception.StockExceptionType;
import com.start.stockdata.identity.dto.request.AbstractRequestDto;

public class CompanyAlreadyExistException extends StockException {

    private static final long serialVersionUID = -8802380804739349143L;
    private static final String DEFAULT_MESSAGE = "Such company already exists";
    private final AbstractRequestDto dto;


    public CompanyAlreadyExistException(AbstractRequestDto dto) {
        super(DEFAULT_MESSAGE);
        this.dto=dto;
    }

    @Override
    public StockExceptionType getType() {
        return StockExceptionType.ENTITY_BY_ID_NOT_FOUND;
    }
}

package com.start.stockdata.exception.exception;

import com.start.stockdata.exception.StockExceptionType;
import com.start.stockdata.identity.dto.request.AbstractRequestDto;

public class FieldAlreadyExistWithinTheCompanyException extends StockException {

    private static final long serialVersionUID = -6079390568479079233L;
    private static final String DEFAULT_MESSAGE = "Such field already exists within the company entity with id=%s";
    private final AbstractRequestDto dto;

    public FieldAlreadyExistWithinTheCompanyException(String mainEntityId, AbstractRequestDto dto) {
        super(String.format(DEFAULT_MESSAGE, mainEntityId));
        this.dto = dto;
    }

    @Override
    public StockExceptionType getType() {
        return StockExceptionType.FIELD_ALREADY_EXISTS_WITHIN_THE_COMPANY;
    }

    @Override
    public Object[] getArguments() {
        return new Object[]{dto};
    }

}

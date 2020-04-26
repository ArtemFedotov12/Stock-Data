package com.start.stockdata.exception.exception;

import com.start.stockdata.exception.StockExceptionType;

public class MainEntityNotFoundException extends StockException {

    private static final long serialVersionUID = 1076386918376019594L;
    private static final String DEFAULT_MESSAGE = "Main entity with id=%s doesn't exist";


    public MainEntityNotFoundException(String mainEntityId) {
        super(String.format(DEFAULT_MESSAGE, mainEntityId));
    }

    @Override
    public StockExceptionType getType() {
        return StockExceptionType.MAIN_ENTITY_BY_ID_NOT_FOUND;
    }

}

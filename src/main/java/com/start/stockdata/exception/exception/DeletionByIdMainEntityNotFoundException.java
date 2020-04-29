package com.start.stockdata.exception.exception;

import com.start.stockdata.exception.StockExceptionType;

public class DeletionByIdMainEntityNotFoundException extends StockException {

    private static final long serialVersionUID = 5234689779835450103L;
    private static final String DEFAULT_MESSAGE = "Main entity with id=%s doesn't have any attributes";

    public DeletionByIdMainEntityNotFoundException(String mainEntityId) {
        super(String.format(DEFAULT_MESSAGE, mainEntityId));
    }

    @Override
    public StockExceptionType getType() {
        return StockExceptionType.ATTRIBUTES_BY_ID_MAIN_ENTITY_NOT_FOUND;
    }

}

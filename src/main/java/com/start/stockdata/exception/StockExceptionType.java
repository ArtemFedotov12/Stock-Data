package com.start.stockdata.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Getter
public enum  StockExceptionType {

    ENTITY_BY_ID_NOT_FOUND("STOCK-001", BAD_REQUEST),
    ENTITY_ALREADY_EXISTS("STOCK-002", BAD_REQUEST),
    ENTITY_WITH_SUCH_ID_NOT_FOUND("STOCK-003", BAD_REQUEST),
    USER_BY_ID_NOT_FOUND("STOCK-004", INTERNAL_SERVER_ERROR),
    COMPANY_NOT_EXISTS("STOCK-005", BAD_REQUEST),
    UNDEFINED_EXCEPTION("STOCK-500", INTERNAL_SERVER_ERROR);

    private static final Map<String, StockExceptionType> types = new HashMap<>();

    private final String code;
    private final HttpStatus status;

    StockExceptionType(String code, HttpStatus status) {
        this.code = code;
        this.status = status;
    }

    public static StockExceptionType getByCode(String code) {
        if (types.isEmpty()) {
            fillTypes();
        }
        return types.get(code);
    }

    private static void fillTypes() {
        Arrays.stream(values()).forEach(type -> types.put(type.code, type));
    }
}

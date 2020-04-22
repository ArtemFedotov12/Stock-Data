package com.start.stockdata.exception.wrapper;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data(staticConstructor = "wrap")
public class StockExceptionWrapper {
    private final String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    private final int status;
    private final String error;
    private final String message;
    private final String code;

}

package com.start.stockdata.exception.wrapper;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data(staticConstructor = "wrap")
public class StockExceptionWrapper {
    private final String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    private final String reason;
    private final String code;
}

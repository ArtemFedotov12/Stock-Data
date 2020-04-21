package com.start.stockdata.exception.wrapper;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Data(staticConstructor = "wrap")
public class ValidationExceptionWrapper {
    private final String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    private final String message;
    private final String status;
    Map<String, List<String>> errors;
}

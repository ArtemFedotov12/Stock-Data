package com.start.stockdata.exception.wrapper;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Data(staticConstructor = "wrap")
public class ValidationExceptionWrapper {
    @ApiModelProperty(value = "Time, when validation exception occurred", example = "2020-04-21 12:00:04")
    private final String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    @ApiModelProperty(value = "Validation failed", example = "422")
    private final String message;
    @ApiModelProperty(value = " HTTP status code", example = "422")
    private final int status;
    @ApiModelProperty(value = "Each field can have a set of constraints.", example = "{\n" +
            "        \"factors[].displayName\": [\n" +
            "            \"must not be blank\",\n" +
            "           \"another error\"\n" +
            "        ],\n" +
            "        \"fields[].asset\": [\n" +
            "            \"must not be blank\"\n" +
            "        ],\n" +
            "        \"factors[].asset\": [\n" +
            "            \"must not be blank\"\n" +
            "        ]\n" +
            "    }")
    private final Map<String, List<String>> errors;
}

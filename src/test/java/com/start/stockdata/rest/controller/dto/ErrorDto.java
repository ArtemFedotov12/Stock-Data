package com.start.stockdata.rest.controller.dto;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class ErrorDto {
    @EqualsAndHashCode.Exclude
    private String timestamp;
    private int status;
    private String error;
    private String message;
    private String code;
}

package com.start.stockdata.rest.response;

import lombok.Data;

@Data(staticConstructor = "of")
public class StringResponse {
    private final String response;
}

package com.start.stockdata.rest.response;

import lombok.Data;

@Data(staticConstructor = "of")
public class LongResponse {
    private final Long number;
}

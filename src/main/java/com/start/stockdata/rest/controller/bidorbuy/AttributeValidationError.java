package com.start.stockdata.rest.controller.bidorbuy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttributeValidationError {

    private String attribute;

    private int line;

    private String currentValue;

    private String errorText;

    private String recommendation;

}


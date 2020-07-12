package com.start.stockdata.rest.controller.bidorbuy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeedValidationResultDto {

    private String url;

    private ValidationStatus validationStatus;

    private List<AttributeValidationError> validationErrors;
}

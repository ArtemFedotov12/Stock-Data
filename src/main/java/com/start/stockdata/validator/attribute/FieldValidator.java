package com.start.stockdata.validator.attribute;

import com.start.stockdata.identity.dto.request.AbstractRequestDto;

public interface FieldValidator<RQ extends AbstractRequestDto>
        extends AttributeValidator<RQ, Long> {
}

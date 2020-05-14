package com.start.stockdata.validator.attribute;

import com.start.stockdata.identity.dto.request.AbstractRequestDto;

import java.io.Serializable;

public interface FieldValidator<RQ extends AbstractRequestDto>
        extends AttributeValidator<RQ, Long> {
}

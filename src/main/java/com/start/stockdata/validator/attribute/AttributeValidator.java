package com.start.stockdata.validator.attribute;

import com.start.stockdata.identity.dto.request.AbstractRequestDto;

import java.io.Serializable;

public interface AttributeValidator<RQ extends AbstractRequestDto, ID extends Serializable> {

    void validate(ID mainEntityId);

    void validate(ID mainEntityId, RQ requestDto);

    void validate(ID mainEntityId, ID id, RQ requestDto);

    void validate(ID mainEntityId, ID id);
}

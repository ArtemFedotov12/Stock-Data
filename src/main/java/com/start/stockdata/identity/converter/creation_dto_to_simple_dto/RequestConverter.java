package com.start.stockdata.identity.converter.creation_dto_to_simple_dto;

public interface RequestConverter<CD, SD> {

    SD convert(CD creationDto);

}

package com.start.stockdata.identity.converter.response;

public interface ResponseConverter<E, D> {

    E toEntity(D dto);

    D toDto(E entity);

}

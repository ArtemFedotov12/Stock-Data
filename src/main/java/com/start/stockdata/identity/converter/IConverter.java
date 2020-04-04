package com.start.stockdata.identity.converter;

public interface IConverter<E, D> {

    E toEntity(D dto);

    D toDto(E entity);
}

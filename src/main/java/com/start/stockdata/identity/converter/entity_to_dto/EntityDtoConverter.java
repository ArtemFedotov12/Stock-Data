package com.start.stockdata.identity.converter.entity_to_dto;

public interface EntityDtoConverter<E, D> {

    E toEntity(D dto);

    D toDto(E entity);
}

package com.start.stockdata.wrapper.global;

import com.start.stockdata.identity.dto.request.AbstractRequestDto;
import com.start.stockdata.identity.model.AbstractEntity;
import com.start.stockdata.repository.AbstractEntityRepo;

import java.util.List;
import java.util.Optional;

public interface StockWrapper<
        E ,
        RQ ,
        R ,
        T
        > {

    E save(RQ dto);

    E update(T id, RQ dto);

    Optional<E> findById(T id);

    List<E> findAll();

    void delete(T id);

    Long count(boolean includeDeleted);

}

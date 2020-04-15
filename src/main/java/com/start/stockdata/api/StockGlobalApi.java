package com.start.stockdata.api;

import com.start.stockdata.rest.response.LongResponse;

import java.util.List;

public interface StockGlobalApi<RQ,RS> {

    RS add(
            final RQ entity
    );

    void update(
            final RQ entity
    );

    RS getById(
            final Long id
    );

    List<RS> getAll(
            final int page,
            final int limit
    );

    List<RS> getAll(
            final String order, // todo: String -> Sort.RSirection
            final String attribute,
            final int page,
            final int limit
    );

    List<RS> getAll();

    void delete(
            final Long id
    );

    LongResponse count(
            final boolean includeDeleted
    );

}

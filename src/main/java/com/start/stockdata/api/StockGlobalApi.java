package com.start.stockdata.api;

import com.start.stockdata.identity.dto.request.AbstractRequestDto;
import com.start.stockdata.identity.dto.response.AbstractResponseDto;
import com.start.stockdata.rest.response.LongResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface StockGlobalApi<RQ extends AbstractRequestDto, RS extends AbstractResponseDto> {

    ResponseEntity<RS> add(
            final RQ requestDto
    );

    ResponseEntity<RS> saveOrUpdate(
             Long id,
             RQ requestDto
    );

    ResponseEntity<RS> update(
             Long id,
             RQ requestDto
    );

    ResponseEntity<RS> delete(
            final Long id
    );

    ResponseEntity<RS> getById(
            final Long id
    );

    List<RS> findAll(
            final int page,
            final int limit
    );

    List<RS> findAll(
            final String order, // todo: String -> Sort.RSirection
            final String attribute,
            final int page,
            final int limit
    );

    ResponseEntity<List<RS>> findAll();


    ResponseEntity<LongResponse> count(
            final boolean includeDeleted
    );

}

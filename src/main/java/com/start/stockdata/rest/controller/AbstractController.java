package com.start.stockdata.rest.controller;

import com.start.stockdata.api.StockGlobalApi;
import com.start.stockdata.identity.dto.request.AbstractRequestDto;
import com.start.stockdata.identity.dto.response.AbstractResponseDto;
import com.start.stockdata.rest.response.LongResponse;
import com.start.stockdata.service.AbstractService;
import com.sun.istack.NotNull;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public class AbstractController<
        RQ extends AbstractRequestDto,
        RS extends AbstractResponseDto,
        S extends AbstractService<RQ, RS, ?>
        > implements StockGlobalApi<RQ,RS> {

    protected final S service;

    public AbstractController(S service) {
        this.service = service;
    }

    @ApiOperation(
            value = "Add entity",
            notes = "Method allow to add entity"
    )
    @PostMapping
    public RS add(@Valid @RequestBody RQ creationDto) {
        return service.save(creationDto);
    }


  /*  @ApiOperation(
            value = "Change entity",
            notes = "Method allow to change entity."
    )
    @PutMapping*/
  @Override
    public void update(/*@Valid @RequestBody*/ RQ entity) {

    }

   /* @ApiOperation(
            value = "Find entity by id",
            notes = "The method allows to search for an entity by a unique identifier"
    )
    @GetMapping("{id}")*/
   @Override
    public RS getById(/*@PathVariable("id")*/ Long id) {
        return null;
    }

    @Override
    public List<RS> getAll(int page, int limit) {
        return null;
    }

    @Override
    public List<RS> getAll(String order, String attribute, int page, int limit) {
        return null;
    }

    @Override
    public List<RS> getAll() {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public LongResponse count(boolean includeDeleted) {
        return null;
    }
}

package com.start.stockdata.rest.controller;

import com.start.stockdata.api.StockGlobalApi;
import com.start.stockdata.identity.dto.request.AbstractRequestDto;
import com.start.stockdata.identity.dto.response.AbstractResponseDto;
import com.start.stockdata.rest.response.LongResponse;
import com.start.stockdata.service.AbstractService;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@SwaggerDefinition(securityDefinition = @SecurityDefinition(
        apiKeyAuthDefinitions = {
                @ApiKeyAuthDefinition(key = "custom",
                        name = "Authorization",
                        in = ApiKeyAuthDefinition.ApiKeyLocation.HEADER,
                        description = "Bearer Authentication")}))
public class AbstractController<
        RQ extends AbstractRequestDto,
        RS extends AbstractResponseDto,
        S extends AbstractService<RQ, RS, ?>
        > implements StockGlobalApi<RQ, RS> {

    protected final S service;

    public AbstractController(S service) {
        this.service = service;
    }

    @ApiOperation(
            authorizations = @Authorization("custom"),
            value = "Add entity",
            notes = "Method allow to add entity"
    )
    @PostMapping
    public ResponseEntity<RS> add(@Valid @RequestBody final RQ requestDto) {
        return new ResponseEntity<>(service.save(requestDto), HttpStatus.OK);
    }


    @ApiOperation(
            value = "Change entity",
            notes = "Method allows only changing the entity"
    )
    @PutMapping
    public ResponseEntity<RS> saveOrUpdate(@PathVariable("id") final Long id,
                                           @Valid @RequestBody final RQ requestDto) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Change entity",
            notes = "Method allow only to change entity."
    )
    @PatchMapping
    public ResponseEntity<RS> update(@PathVariable("id") final Long id,
                                     @Valid @RequestBody final RQ requestDto) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Delete entity",
            notes = "Removing an entity by a given unique identifier"
    )
    @DeleteMapping("{id}")
    public ResponseEntity<RS> delete(@PathVariable("id") final Long id) {
        return new ResponseEntity<>(service.delete(id), HttpStatus.OK);
    }


    @ApiOperation(
            value = "Find entity by id",
            notes = "The method allows to search for an entity by a unique identifier"
    )
    @GetMapping("{id}")
    public ResponseEntity<RS> getById(@PathVariable("id") Long id) {
        return null;
    }


    @ApiOperation(
            value = "Find all entities",
            notes = "Find all entities, specified type"
    )
    @GetMapping
    public ResponseEntity<List<RS>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }


    @Override
    public ResponseEntity<LongResponse> count(
            @RequestParam(value = "includeDeleted", defaultValue = "false") final boolean includeDeleted) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}

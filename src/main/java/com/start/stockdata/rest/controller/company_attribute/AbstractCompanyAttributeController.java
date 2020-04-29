package com.start.stockdata.rest.controller.company_attribute;

import com.start.stockdata.identity.dto.request.AbstractRequestDto;
import com.start.stockdata.identity.dto.response.AbstractResponseDto;
import com.start.stockdata.service.company_attribute.AttributeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public abstract class AbstractCompanyAttributeController<
        RQ extends AbstractRequestDto,
        RS extends AbstractResponseDto,
        S extends AttributeService<RQ, RS, Long>
        >
        implements CompanyAttributeController<RQ, RS, Long> {

    protected final S service;

    public AbstractCompanyAttributeController(S service) {
        this.service = service;
    }

    @ApiOperation(
            value = "Add entity",
            notes = "Method allow to add entity"
    )
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RS> save(@PathVariable("mainEntityId") Long mainEntityId,
                                   @Valid RQ requestDto) {
        return new ResponseEntity<>(service.save(mainEntityId, requestDto), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Change entity",
            notes = "Method allows only changing the entity"
    )
    @PutMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RS> update(@PathVariable("mainEntityId") Long mainEntityId,
                                     @PathVariable("id") Long id,
                                     @Valid RQ requestDto) {
        return new ResponseEntity<>(service.update(mainEntityId, id, requestDto), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Delete entity",
            notes = "Removing an entity by a given unique identifier"
    )
    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RS> delete(@PathVariable("mainEntityId") Long mainEntityId,
                                     @PathVariable("id") Long id) {
        return new ResponseEntity<>(service.delete(mainEntityId, id), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Delete entity",
            notes = "Removing an entity by a given unique identifier"
    )
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RS>> deleteAllByCompanyId(@PathVariable("mainEntityId") Long mainEntityId) {
        return new ResponseEntity<>(service.deleteAllByCompanyId(mainEntityId), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Find entity by id",
            notes = "The method allows to search for an entity by a unique identifier"
    )
    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RS> findById(@PathVariable("mainEntityId") Long mainEntityId,
                                       @PathVariable("id") Long id) {
        return new ResponseEntity<>(service.findById(mainEntityId, id), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Find all entities",
            notes = "Find all entities of the specified type"
    )
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RS>> findAllByCompanyId(@PathVariable("mainEntityId") Long mainEntityId) {
        return new ResponseEntity<>(service.findAllByCompanyId(mainEntityId), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Amount of entities",
            notes = "Count amount of entities of the specified type"
    )
    @GetMapping(value = "count", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> count(@PathVariable("mainEntityId") Long mainEntityId,
                                      @RequestParam(value = "deleted", defaultValue = "false") boolean includeDeleted) {
        return new ResponseEntity<>(service.count(mainEntityId, includeDeleted), HttpStatus.OK);
    }

}



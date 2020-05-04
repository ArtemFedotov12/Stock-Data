package com.start.stockdata.rest.controller.attribute;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

public interface AttributeController<
        RQ ,
        RS ,
        ID
        > {


    ResponseEntity<RS> save(
            @PathVariable("mainEntityId") ID mainEntityId,
            @Valid @RequestBody RQ requestDto
    );

    ResponseEntity<RS> update(
            @PathVariable("mainEntityId") ID mainEntityId,
            @PathVariable("id") ID id,
            @Valid @RequestBody RQ requestDto
    );

    ResponseEntity<RS> delete(
            @PathVariable("mainEntityId") ID mainEntityId,
            @PathVariable("id") final ID id
    );

    ResponseEntity<List<RS>> deleteAllByMainEntityId(
            @PathVariable("id") final ID mainEntityId
    );

    ResponseEntity<RS> findById(
            @PathVariable("mainEntityId") ID mainEntityId,
            @PathVariable("id") final ID id
    );

    ResponseEntity<List<RS>> findAllByMainEntityId(
            @PathVariable("mainEntityId") final ID mainEntityId
    );

    ResponseEntity<ID> count(
            @PathVariable("mainEntityId") final ID mainEntityId,
            @RequestParam(value = "deleted", defaultValue = "false") final boolean includeDeleted
    );


}

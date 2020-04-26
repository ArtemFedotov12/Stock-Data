package com.start.stockdata.rest.controller.company_attribute;

import com.start.stockdata.identity.dto.request.AbstractRequestDto;
import com.start.stockdata.identity.dto.response.AbstractResponseDto;
import com.start.stockdata.service.company_attribute.AttributeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

public interface CompanyAttributeController<
        RQ ,
        RS ,
        T
        > {


    ResponseEntity<RS> save(
            @PathVariable("companyId") T companyId,
            @Valid @RequestBody RQ requestDto
    );

    ResponseEntity<RS> update(
            @PathVariable("companyId") T companyId,
            @PathVariable("id") T id,
            @Valid @RequestBody RQ requestDto
    );

    ResponseEntity<RS> delete(
            @PathVariable("companyId") T companyId,
            @PathVariable("id") final T id
    );

    ResponseEntity<List<RS>> deleteAllByCompanyId(
            @PathVariable("id") final T companyId
    );

    ResponseEntity<RS> findById(
            @PathVariable("companyId") T companyId,
            @PathVariable("id") final T id
    );

    ResponseEntity<List<RS>> findAllByCompanyId(
            @PathVariable("id") final T companyId
    );

    ResponseEntity<T> count(
            @PathVariable("id") final T companyId,
            final boolean includeDeleted
    );


}

package com.start.stockdata.rest.controller.company_attribute;

import com.start.stockdata.identity.dto.request.AbstractRequestDto;
import com.start.stockdata.identity.dto.response.AbstractResponseDto;
import com.start.stockdata.rest.response.LongResponse;
import com.start.stockdata.service.company_attribute.CompanyAttributeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

public interface CompanyAttributeController<
        RQ extends AbstractRequestDto,
        RS extends AbstractResponseDto,
        S extends CompanyAttributeService<?, RS, RQ, ?, ?>> {


    ResponseEntity<RS> save(
            @PathVariable("companyId") Long companyId,
            @Valid @RequestBody RQ requestDto
    );

    ResponseEntity<RS> update(
            @PathVariable("id") Long id,
            @Valid @RequestBody RQ requestDto
    );

    ResponseEntity<RS> delete(
            @PathVariable("id") final Long id
    );

    ResponseEntity<List<RS>> deleteAllByCompanyId(
            @PathVariable("id") final Long companyId
    );

    ResponseEntity<RS> findById(
            @PathVariable("id") final Long id
    );

    ResponseEntity<List<RS>> findAllByCompanyId(
            @PathVariable("id") final Long companyId
    );

    ResponseEntity<LongResponse> count(
            final boolean includeDeleted
    );


}

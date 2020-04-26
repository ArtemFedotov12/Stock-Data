package com.start.stockdata.rest.controller.company_attribute;

import com.start.stockdata.identity.dto.request.AbstractRequestDto;
import com.start.stockdata.identity.dto.response.AbstractResponseDto;
import com.start.stockdata.service.company_attribute.AttributeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

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
    @Override
    public ResponseEntity<RS> save(Long companyId, @Valid RQ requestDto) {
        return new ResponseEntity<>(service.save(companyId, requestDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RS> update(Long companyId, Long id, @Valid RQ requestDto) {
        return new ResponseEntity<>(service.update(companyId, id, requestDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RS> delete(Long companyId, Long id) {
        return new ResponseEntity<>(service.delete(companyId, id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<RS>> deleteAllByCompanyId(Long companyId) {
        return new ResponseEntity<>(service.deleteAllByCompanyId(companyId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RS> findById(Long companyId, Long id) {
        return new ResponseEntity<>(service.findById(companyId, id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<RS>> findAllByCompanyId(Long companyId) {
        return new ResponseEntity<>(service.findAllByCompanyId(companyId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Long> count(Long companyId, boolean includeDeleted) {
        return new ResponseEntity<>(service.count(companyId, includeDeleted), HttpStatus.OK);
    }

}



package com.start.stockdata.rest.controller.company_attribute;

import com.start.stockdata.identity.dto.request.AbstractRequestDto;
import com.start.stockdata.identity.dto.response.AbstractResponseDto;
import com.start.stockdata.rest.response.LongResponse;
import com.start.stockdata.service.company_attribute.CompanyAttributeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.util.List;

public abstract class AbstractCompanyAttributeController<
        RQ extends AbstractRequestDto,
        RS extends AbstractResponseDto,
        S extends CompanyAttributeService<?, RS, RQ, ?>
        >
        implements CompanyAttributeController<RQ, RS, S> {

    protected final S service;

    public AbstractCompanyAttributeController(S service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<RS> save(Long companyId, @Valid RQ requestDto) {
        return new ResponseEntity<>(service.save(companyId, requestDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RS> update(Long id, @Valid RQ requestDto) {
        return new ResponseEntity<>(service.update(id, requestDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RS> delete(Long id) {
        return new ResponseEntity<>(service.delete(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<RS>> deleteAllByCompanyId(Long companyId) {
        return new ResponseEntity<>(service.deleteAllByCompanyId(companyId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RS> findById(Long id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<RS>> findAllByCompanyId(Long companyId) {
        return new ResponseEntity<>(service.findAllByCompanyId(companyId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<LongResponse> count(boolean includeDeleted) {
        return new ResponseEntity<>(LongResponse.of(service.count(includeDeleted)), HttpStatus.OK);
    }

}

package com.start.stockdata.wrapper.attributes;

import com.start.stockdata.identity.converter.active.ServiceConverter;
import com.start.stockdata.identity.dto.active.CompanyFieldActiveDto;
import com.start.stockdata.identity.model.CompanyField;
import com.start.stockdata.repository.CompanyFieldRepo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FieldWrapper extends AbstractAttributeWrapper<
        CompanyField,
        CompanyFieldActiveDto,
        CompanyFieldRepo
        > {

    public FieldWrapper(ServiceConverter<CompanyFieldActiveDto, CompanyField, ?, ?> serviceConverter, CompanyFieldRepo repository) {
        super(serviceConverter, repository);
    }

    @Override
    public void deleteAllByCompanyId(Long mainEntityId) {
        repository.deleteAll();
    }

    @Override
    public List<CompanyField> findAllByCompanyId(Long mainEntityId) {
        return repository.findAllByCompanyId(mainEntityId);
    }

    @Override
    public Long count(Long mainEntityId, boolean includeDeleted) {
        return repository.count(mainEntityId);
    }
}

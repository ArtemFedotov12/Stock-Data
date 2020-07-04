package com.start.stockdata.wrapper.attributes.field;

import com.start.stockdata.exception.exception.CompanyByIdNotFoundException;
import com.start.stockdata.exception.exception.SaveFieldException;
import com.start.stockdata.identity.model.Company;
import com.start.stockdata.identity.model.Field;
import com.start.stockdata.repository.FieldRepo;
import com.start.stockdata.wrapper.global.company.CompanyWrapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DefaultFieldWrapper implements FieldWrapper {

    private final FieldRepo fieldRepo;
    private final CompanyWrapper companyWrapper;

    public DefaultFieldWrapper(FieldRepo fieldRepo, CompanyWrapper companyWrapper) {
        this.fieldRepo = fieldRepo;
        this.companyWrapper = companyWrapper;
    }

    @Override
    public Field save(Long mainEntityId, Field field) {
        Company company = getCompanyById(mainEntityId);
        company.addField(field);

        // Here company in detached state,
        // so we specified CascadeType.MERGE to overcome this
        Optional<Field> fieldOptional = companyWrapper.save(company)
                .getFields()
                .stream()
                .filter(item -> item.equals(field))
                .findFirst();

        if (fieldOptional.isPresent()) {
            return fieldOptional.get();
        } else {
            throw new SaveFieldException();
        }
    }

    @Override
    public Field update(Long mainEntityId, Long id, Field field) {
        Company company = getCompanyById(mainEntityId);
        company.getFields().removeIf(item -> item.getId().equals(id));
        field.setId(id);
        company.addField(field);
        companyWrapper.save(company);
        return field;
    }

    @Override
    public Optional<Field> findById(Long id) {
        return fieldRepo.findById(id);
    }

    @Override
    public List<Field> findAllByMainEntityId(Long mainEntityId) {
        return fieldRepo.findAllByCompanyId(mainEntityId);
    }

    @Override
    public void delete(Long mainEntityId, Field field) {
        Company company = getCompanyById(mainEntityId);
        company.removeField(field);
        companyWrapper.save(company);
    }

    @Override
    public void deleteAllByMainEntityId(Long mainEntityId) {
        Company company = getCompanyById(mainEntityId);
        company.removeAllFields();
        companyWrapper.save(company);
    }

    @Override
    public Long count(Long mainEntityId, boolean includeDeleted) {
        return fieldRepo.count(mainEntityId);
    }

    private Company getCompanyById(Long id) {
        Optional<Company> optionalCompany = companyWrapper.findById(id);
        if (optionalCompany.isPresent()) {
            return optionalCompany.get();
        } else {
            throw new CompanyByIdNotFoundException(id);
        }
    }

}

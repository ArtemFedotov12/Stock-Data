package com.start.stockdata.wrapper.attributes;

import com.start.stockdata.exception.exception.CompanyByIdNotFoundException;
import com.start.stockdata.exception.exception.SaveFieldException;
import com.start.stockdata.identity.model.Company;
import com.start.stockdata.identity.model.Field;
import com.start.stockdata.wrapper.global.CompanyWrapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CompanyFieldWrapper implements CompanyFieldAttributeWrapper {
    
    private final CompanyWrapper companyWrapper;

    public CompanyFieldWrapper(CompanyWrapper companyWrapper) {
        this.companyWrapper = companyWrapper;
    }

    public Field saveField(Long companyId, Field field) {
        Company company = getCompanyById(companyId);
        company.addField(field);

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

    public Field updateField(Long companyId, Long fieldId, Field field) {
        Company company = getCompanyById(companyId);
        company.getFields().removeIf(item -> item.getId().equals(fieldId));

        field.setId(fieldId);
        company.addField(field);
        companyWrapper.save(company);
        return field;
    }

    public void deleteField(Long companyId, Field field) {
        Company company = getCompanyById(companyId);
        company.removeField(field);
        companyWrapper.save(company);
    }

    public void deleteAllFields(Long companyId) {
        Company company = getCompanyById(companyId);
        company.getFields().clear();
        companyWrapper.save(company);
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

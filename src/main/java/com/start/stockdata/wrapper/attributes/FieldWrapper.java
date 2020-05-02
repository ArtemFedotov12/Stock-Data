package com.start.stockdata.wrapper.attributes;

import com.start.stockdata.identity.model.Field;
import com.start.stockdata.repository.FieldRepo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class FieldWrapper implements AttributeWrapper<Field, Long> {

    private final FieldRepo fieldRepo;
    private final CompanyFieldAttributeWrapper companyFieldWrapper;

    public FieldWrapper(FieldRepo fieldRepo,
                        CompanyFieldAttributeWrapper companyFieldWrapper) {
        this.fieldRepo = fieldRepo;
        this.companyFieldWrapper = companyFieldWrapper;
    }


    @Override
    public Field save(Long mainEntityId, Field field) {
        return companyFieldWrapper.saveField(mainEntityId, field);
    }

    @Override
    public Field update(Long mainEntityId, Long id, Field entity) {
        return companyFieldWrapper.updateField(mainEntityId, id, entity);
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
        companyFieldWrapper.deleteField(mainEntityId, field);
    }

    @Override
    public void deleteAllByMainEntityId(Long mainEntityId) {
        companyFieldWrapper.deleteAllFields(mainEntityId);
    }

    @Override
    public Long count(Long mainEntityId, boolean includeDeleted) {
        return fieldRepo.count(mainEntityId);
    }

}

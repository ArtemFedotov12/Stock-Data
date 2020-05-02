package com.start.stockdata.wrapper.attributes;

import com.start.stockdata.identity.model.Field;

public interface CompanyFieldAttributeWrapper {

    Field saveField(Long companyId, Field field);

    Field updateField(Long companyId, Long fieldId, Field field);

    void deleteField(Long companyId, Field field);

    void deleteAllFields(Long companyId);

}

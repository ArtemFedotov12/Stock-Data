package com.start.stockdata.util.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class CompanyTypeAttributeConverter implements AttributeConverter<CompanyType, String> {

    @Override
    public String convertToDatabaseColumn(CompanyType attribute) {

        if (attribute != null) {
            return attribute.getValue();
        } else {
            throw new IllegalArgumentException("Company type is null");
        }

    }

    @Override
    public CompanyType convertToEntityAttribute(String dbData) {

        if (dbData != null && CompanyType.contains(dbData)) {
            return CompanyType.getCompanyTypeByValue(dbData);
        } else {
            throw new IllegalArgumentException("Company type is null");
        }

    }
}

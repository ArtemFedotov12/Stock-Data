package com.start.stockdata.util.constraints;

import com.start.stockdata.util.enums.CompanyType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CompanyValidator implements ConstraintValidator<Company, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return CompanyType.contains(value);
    }

}

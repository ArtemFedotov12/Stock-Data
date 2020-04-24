package com.start.stockdata.validations;

import com.start.stockdata.service.stock_global.CompanyService;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class CompanyValidator implements ConstraintValidator<Company, String> {

    private final CompanyService companyService;

    public CompanyValidator(CompanyService companyService) {
        this.companyService = companyService;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return companyService.findAll().contains(value);
    }

}

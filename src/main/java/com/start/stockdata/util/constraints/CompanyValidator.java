package com.start.stockdata.util.constraints;

import com.start.stockdata.service.CompanyService;
import com.start.stockdata.util.enums.CompanyType;
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

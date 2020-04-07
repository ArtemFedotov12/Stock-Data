package com.start.stockdata.identity.converter;

import com.start.stockdata.identity.dto.CompanyDto;
import com.start.stockdata.identity.model.Company;
import com.start.stockdata.util.enums.CompanyType;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
public class CompanyConverter implements IConverter<Company, CompanyDto> {

    @Override
    public Company toEntity(CompanyDto dto) {
        return ofNullable(dto)
                .map(companyDto -> {
                    Company company = new Company();
                    company.setName(companyDto.getName());
                    company.setCompanyType(companyDto.getCompanyType());
                    company.setUserId(companyDto.getUserId());
                    return company;
                })
                .orElse(null);
    }

    @Override
    public CompanyDto toDto(Company entity) {

        return ofNullable(entity)
                .map(companyEntity -> {
                    CompanyDto companyDto = new CompanyDto();
                    companyDto.setName(companyEntity.getName());
                    companyDto.setCompanyType(companyEntity.getCompanyType());
                    companyDto.setUserId(companyEntity.getUserId());
                    return companyDto;
                })
                .orElse(null);
    }

}

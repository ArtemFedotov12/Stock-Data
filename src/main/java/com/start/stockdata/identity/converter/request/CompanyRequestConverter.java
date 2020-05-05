package com.start.stockdata.identity.converter.request;

import com.start.stockdata.identity.dto.request.FactorRequestDto;
import com.start.stockdata.identity.dto.request.FieldRequestDto;
import com.start.stockdata.identity.dto.request.company.CompanyRequestDto;
import com.start.stockdata.identity.model.Company;
import com.start.stockdata.identity.model.Factor;
import com.start.stockdata.identity.model.Field;
import com.start.stockdata.identity.model.CompanyType;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.stream.Collectors;
import static java.util.Optional.ofNullable;

@Component
public class CompanyRequestConverter implements RequestConverter<Company, CompanyRequestDto>  {

    private final RequestConverter<Factor, FactorRequestDto> factorConverter;
    private  final RequestConverter<Field, FieldRequestDto> fieldConverter;

    public CompanyRequestConverter(RequestConverter<Factor, FactorRequestDto> factorConverter,
                                   RequestConverter<Field, FieldRequestDto> fieldConverter) {
        this.factorConverter = factorConverter;
        this.fieldConverter = fieldConverter;
    }

    @Override
    public Company toEntity(CompanyRequestDto requestDto) {
        return ofNullable(requestDto)
                .map(item -> {
                    Company company = new Company();
                    company.setName(item.getName());
                    company.setCompanyTypes(item
                            .getTypes()
                            .stream()
                            .map(companyTypeIdDto -> {
                                CompanyType companyType = new CompanyType();
                                companyType.setId(companyTypeIdDto.getId());
                                return companyType;
                            })
                            .collect(Collectors.toCollection(()->new TreeSet<CompanyType>(Comparator.comparingLong(CompanyType::getId)))));
                    company.setFields(item
                            .getFields()
                            .stream()
                            .map(fieldConverter::toEntity)
                            .collect(Collectors.toSet()));
                    company.setFactors(item
                            .getFactors()
                            .stream()
                            .map(factorConverter::toEntity)
                            .collect(Collectors.toSet()));
                    return company;
                })
                .orElse(null);
    }

}

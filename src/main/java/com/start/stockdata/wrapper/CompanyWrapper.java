package com.start.stockdata.wrapper;

import com.start.stockdata.identity.converter.IConverter;
import com.start.stockdata.identity.dto.CompanyDto;
import com.start.stockdata.identity.model.Company;
import com.start.stockdata.repository.AbstractEntityRepository;
import org.springframework.stereotype.Component;

@Component
public class CompanyWrapper extends AbstractEntityDtoWrapper<Company, CompanyDto>{
    public CompanyWrapper(IConverter<Company, CompanyDto> converter, AbstractEntityRepository<Company> repository) {
        super(converter, repository);
    }


}

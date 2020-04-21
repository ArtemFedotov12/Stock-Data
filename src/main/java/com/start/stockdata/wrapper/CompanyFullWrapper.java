package com.start.stockdata.wrapper;

import com.start.stockdata.identity.converter.entity_to_dto.EntityDtoConverter;
import com.start.stockdata.identity.dto.response.CompanyFullDto;
import com.start.stockdata.identity.model.Company;
import com.start.stockdata.repository.CompanyRepo;
import org.springframework.stereotype.Component;

@Component
public class CompanyFullWrapper extends AbstractEntityDtoWrapper<Company, CompanyFullDto, CompanyRepo>{

    public CompanyFullWrapper(EntityDtoConverter<Company, CompanyFullDto> converter, CompanyRepo repository) {
        super(converter, repository);
    }

}

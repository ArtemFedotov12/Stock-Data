package com.start.stockdata.wrapper;

import com.start.stockdata.identity.converter.entity_to_dto.EntityDtoConverter;
import com.start.stockdata.identity.dto.CompanyDto;
import com.start.stockdata.identity.model.Company;
import com.start.stockdata.repository.CompanyRepo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CompanyWrapper extends AbstractEntityDtoWrapper<Company, CompanyDto, CompanyRepo>{

    public CompanyWrapper(EntityDtoConverter<Company, CompanyDto> converter, CompanyRepo repository) {
        super(converter, repository);
    }

    public List<CompanyDto> findAllByUserId(long id) {
        return repository.findAllByUserId(id)
                .stream()
                .map(converter::toDto)
                .collect(Collectors.toList());
    }


}

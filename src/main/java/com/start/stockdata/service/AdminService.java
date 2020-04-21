package com.start.stockdata.service;

import com.start.stockdata.identity.converter.entity_to_dto.CompanyFullConverter;
import com.start.stockdata.identity.converter.entity_to_dto.EntityDtoConverter;
import com.start.stockdata.identity.dto.response.CompanyFullDto;
import com.start.stockdata.identity.model.Company;
import com.start.stockdata.repository.CompanyRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {

    private final CompanyRepo companyRepo;
    private final EntityDtoConverter<Company, CompanyFullDto> converter;

    public AdminService(CompanyRepo companyRepo,
                        EntityDtoConverter<Company, CompanyFullDto> converter) {
        this.companyRepo = companyRepo;
        this.converter = converter;
    }

    public List<CompanyFullDto> findAll() {
        return convert(companyRepo.findAll());
    }

    protected List<CompanyFullDto> convert(List<Company> entityList) {
        return entityList.stream()
                .map(converter::toDto)
                .collect(Collectors.toList());
    }



}

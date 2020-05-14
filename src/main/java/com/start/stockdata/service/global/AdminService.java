package com.start.stockdata.service.global;

import com.start.stockdata.identity.converter.response.ResponseConverter;
import com.start.stockdata.identity.dto.response.CompanyFullResponseDto;
import com.start.stockdata.identity.model.Company;
import com.start.stockdata.repository.CompanyRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {

    private final CompanyRepo companyRepo;
    private final ResponseConverter<Company, CompanyFullResponseDto> converter;

    public AdminService(CompanyRepo companyRepo,
                        ResponseConverter<Company, CompanyFullResponseDto> converter) {
        this.companyRepo = companyRepo;
        this.converter = converter;
    }

    public List<CompanyFullResponseDto> findAll() {
        return convert(companyRepo.findAll());
    }

    protected List<CompanyFullResponseDto> convert(List<Company> entityList) {
        return entityList.stream()
                .map(converter::toDto)
                .collect(Collectors.toList());
    }



}

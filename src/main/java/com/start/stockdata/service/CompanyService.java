package com.start.stockdata.service;

import com.start.stockdata.identity.converter.CompanyConverter;
import com.start.stockdata.identity.dto.CompanyDto;
import com.start.stockdata.wrapper.CompanyWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyWrapper companyWrapper;
    private final CompanyConverter companyConverter;

    public CompanyDto save(CompanyDto companyDto) {
       return companyWrapper.save(companyDto);
    }

    public List<CompanyDto> findAll() {
        //SecurityContextHolder.getContext().getAuthentication();
        //return companyRepo.findAllById(id).stream().map(companyConverter::toDto).collect(Collectors.toList());
        return  null;
    }


}

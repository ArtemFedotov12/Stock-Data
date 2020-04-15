package com.start.stockdata.service;

import com.start.stockdata.exception.exception.UserByIdNotFoundException;
import com.start.stockdata.identity.converter.creation_dto_to_simple_dto.CompanyRequestConverter;
import com.start.stockdata.identity.dto.request.CompanyRequestDto;
import com.start.stockdata.identity.dto.response.CompanyDto;
import com.start.stockdata.wrapper.CompanyWrapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.start.stockdata.util.SecurityContextUtil.getUserIdFromSecurityContext;

@Service
public class CompanyService {
    private final CompanyWrapper companyWrapper;
    private final CompanyRequestConverter companyRequestConverter;

    public CompanyService(CompanyWrapper companyWrapper, CompanyRequestConverter companyRequestConverter) {
        this.companyWrapper = companyWrapper;
        this.companyRequestConverter = companyRequestConverter;
    }


    public CompanyDto save(CompanyRequestDto companyCreationDto) {
        CompanyDto companyDto = companyRequestConverter.convert(companyCreationDto);

        Optional<Long> optionalUserId = getUserIdFromSecurityContext();

        if (!optionalUserId.isPresent()) {
            throw  new UserByIdNotFoundException();
        } else {
            companyDto.setUserId(optionalUserId.get());
        }

       return companyWrapper.save(companyDto);
    }

    public List<CompanyDto> findAllByUserId() {
        Optional<Long> optionalUserId = getUserIdFromSecurityContext();
        if (!optionalUserId.isPresent()) {
            throw  new UserByIdNotFoundException();
        } else {
            return  companyWrapper.findAllByUserId(optionalUserId.get());
        }

    }

    public List<CompanyDto> findAll() {
        return companyWrapper.findAll();
    }




}

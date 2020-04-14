package com.start.stockdata.service;

import com.start.stockdata.config.userDetails.StockUserInfo;
import com.start.stockdata.exception.exception.UserByIdNotFoundException;
import com.start.stockdata.identity.converter.creation_dto_to_simple_dto.CompanyCreationDtoConverter;
import com.start.stockdata.identity.dto.CompanyCreationDto;
import com.start.stockdata.identity.dto.CompanyDto;
import com.start.stockdata.wrapper.CompanyWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.start.stockdata.util.SecurityContextUtil.getUserIdFromSecurityContext;

@Service
public class CompanyService {
    private final CompanyWrapper companyWrapper;
    private final CompanyCreationDtoConverter companyCreationDtoConverter;

    public CompanyService(CompanyWrapper companyWrapper, CompanyCreationDtoConverter companyCreationDtoConverter) {
        this.companyWrapper = companyWrapper;
        this.companyCreationDtoConverter = companyCreationDtoConverter;
    }


    public CompanyDto save(CompanyCreationDto companyCreationDto) {
        CompanyDto companyDto = companyCreationDtoConverter.convert(companyCreationDto);

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

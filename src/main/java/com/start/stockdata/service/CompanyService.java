package com.start.stockdata.service;

import com.start.stockdata.exception.exception.UserByIdNotFoundException;
import com.start.stockdata.identity.converter.creation_dto_to_simple_dto.CompanyRequestConverter;
import com.start.stockdata.identity.converter.creation_dto_to_simple_dto.RequestConverter;
import com.start.stockdata.identity.dto.request.CompanyRequestDto;
import com.start.stockdata.identity.dto.response.CompanyDto;
import com.start.stockdata.wrapper.CompanyWrapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.start.stockdata.util.SecurityContextUtil.getUserIdFromSecurityContext;

@Service
public class CompanyService extends AbstractService<CompanyRequestDto, CompanyDto, CompanyWrapper> {

    public CompanyService(CompanyWrapper wrapper, RequestConverter<CompanyRequestDto, CompanyDto> converter) {
        super(wrapper, converter);
    }

    @Override
    public CompanyDto save(CompanyRequestDto companyCreationDto) {
        CompanyDto companyDto = converter.convert(companyCreationDto);

        Optional<Long> optionalUserId = getUserIdFromSecurityContext();

        if (!optionalUserId.isPresent()) {
            throw new UserByIdNotFoundException();
        } else {
            companyDto.setUserId(optionalUserId.get());
        }

        return wrapper.save(companyDto);
    }

    public List<CompanyDto> findAllByUserId() {
        Optional<Long> optionalUserId = getUserIdFromSecurityContext();
        if (!optionalUserId.isPresent()) {
            throw new UserByIdNotFoundException();
        } else {
            return wrapper.findAllByUserId(optionalUserId.get());
        }

    }


    @Override
    protected boolean entityAlreadyExists(CompanyDto responseDto) {
        return false;
    }


}

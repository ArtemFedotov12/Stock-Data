package com.start.stockdata.wrapper.global;

import com.start.stockdata.identity.converter.request.RequestConverter;
import com.start.stockdata.identity.dto.request.CompanyTypeRequestDto;
import com.start.stockdata.identity.dto.response.CompanyTypeResponseDto;
import com.start.stockdata.identity.model.CompanyType;
import com.start.stockdata.repository.CompanyTypeRepo;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CompanyTypeWrapper extends AbstractEntityDtoWrapper<CompanyType, CompanyTypeRequestDto, CompanyTypeRepo> {


    public CompanyTypeWrapper(
            RequestConverter<CompanyType, CompanyTypeRequestDto> requestConverter,
            CompanyTypeRepo repository
    ) {
        super(requestConverter, repository);
    }

    public boolean isSameCompanyTypeAlreadyExist(CompanyTypeResponseDto companyTypeResponseDto) {
        Optional<CompanyType> companyTypeOptional = repository.findByType(companyTypeResponseDto.getType());
        return companyTypeOptional.isPresent();
    }

    public Optional<CompanyType> findByType(String companyType) {
        return repository.findByType(companyType);
    }


}

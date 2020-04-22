package com.start.stockdata.wrapper;

import com.start.stockdata.identity.converter.entity_to_dto.ResponseConverter;
import com.start.stockdata.identity.converter.request_to_entity.RequestConverter;
import com.start.stockdata.identity.dto.request.CompanyTypeRequestDto;
import com.start.stockdata.identity.dto.response.CompanyTypeResponseDto;
import com.start.stockdata.identity.model.CompanyType;
import com.start.stockdata.repository.CompanyTypeRepo;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CompanyTypeWrapper extends AbstractEntityDtoWrapper<CompanyType, CompanyTypeResponseDto, CompanyTypeRequestDto, CompanyTypeRepo> {

    public CompanyTypeWrapper(ResponseConverter<CompanyType, CompanyTypeResponseDto> responseConverter,
                              RequestConverter<CompanyType, CompanyTypeRequestDto> requestConverter,
                              CompanyTypeRepo repository) {
        super(responseConverter, requestConverter, repository);
    }

    public boolean isSameCompanyTypeAlreadyExist(CompanyTypeResponseDto companyTypeResponseDto) {
        Optional<CompanyType> companyTypeOptional = repository.findByType(companyTypeResponseDto.getType());
        return companyTypeOptional.isPresent();
    }

    public Optional<CompanyType> findByType(String companyType) {
        return repository.findByType(companyType);
    }


}

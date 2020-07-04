package com.start.stockdata.wrapper.global.companyType;

import com.start.stockdata.identity.dto.response.CompanyTypeResponseDto;
import com.start.stockdata.identity.model.CompanyType;
import com.start.stockdata.repository.CompanyTypeRepo;
import com.start.stockdata.wrapper.global.AbstractGlobalWrapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DefaultCompanyTypeWrapper extends AbstractGlobalWrapper<CompanyType, CompanyTypeRepo>
        implements CompanyTypeWrapper {


    public DefaultCompanyTypeWrapper(CompanyTypeRepo repository) {
        super(repository);
    }

    public boolean isSameCompanyTypeAlreadyExist(CompanyTypeResponseDto companyTypeResponseDto) {
        Optional<CompanyType> companyTypeOptional = repository.findByType(companyTypeResponseDto.getType());
        return companyTypeOptional.isPresent();
    }

    public Optional<CompanyType> findByType(String companyType) {
        return repository.findByType(companyType);
    }


}

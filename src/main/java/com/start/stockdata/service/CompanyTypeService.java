package com.start.stockdata.service;

import com.start.stockdata.exception.exception.EntityByIdNotFoundException;
import com.start.stockdata.identity.converter.entity_to_dto.ResponseConverter;
import com.start.stockdata.identity.dto.request.CompanyTypeRequestDto;
import com.start.stockdata.identity.dto.response.CompanyTypeResponseDto;
import com.start.stockdata.identity.model.CompanyType;
import com.start.stockdata.wrapper.CompanyTypeWrapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CompanyTypeService extends AbstractService<
        CompanyType,
        CompanyTypeRequestDto,
        CompanyTypeResponseDto,
        CompanyTypeWrapper> {

    public CompanyTypeService(
            CompanyTypeWrapper wrapper,
            ResponseConverter<CompanyType, CompanyTypeResponseDto> converter) {
        super(wrapper, converter);
    }

    @Override
    protected boolean entityAlreadyExistsSave(CompanyTypeRequestDto requestDto) {
        Optional<CompanyType> companyTypeOptional = wrapper.findByType(requestDto.getType());
        return companyTypeOptional.isPresent();
    }

    /*
        Specially has been done in this way. For test
        if current 'type=Tech' and user try update type to the same 'type=Tech'
        It will be update(request to Db) and no Exception will be thrown
     */

    @Override
    protected boolean entityAlreadyExistsUpdate(final Long id, CompanyTypeRequestDto requestDto) {
        Optional<CompanyType> companyTypeByIdOptional = wrapper.findById(id);
        if (!companyTypeByIdOptional.isPresent()) {
            throw new EntityByIdNotFoundException(id);
        }

        Optional<CompanyType> companyTypeOptional = wrapper.findByType(requestDto.getType());
        return companyTypeOptional.filter(companyType -> !id.equals(companyType.getId())).isPresent();
    }
}

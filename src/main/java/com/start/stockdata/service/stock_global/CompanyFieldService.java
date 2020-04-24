package com.start.stockdata.service.stock_global;

import com.start.stockdata.identity.converter.entity_to_dto.ResponseConverter;
import com.start.stockdata.identity.dto.request.CompanyFieldRequestDto;
import com.start.stockdata.identity.dto.response.CompanyFieldResponseDto;
import com.start.stockdata.identity.model.CompanyField;
import com.start.stockdata.service.company_attribute.CompanyAttributeService;
import com.start.stockdata.wrapper.stock_global.CompanyFieldWrapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CompanyFieldService extends AbstractService<
        CompanyField,
        CompanyFieldRequestDto,
        CompanyFieldResponseDto,
        CompanyFieldWrapper
        >
        implements CompanyAttributeService<CompanyFieldResponseDto, CompanyFieldRequestDto> {

    public CompanyFieldService(
            CompanyFieldWrapper wrapper,
            ResponseConverter<CompanyField, CompanyFieldResponseDto> converter
    ) {
        super(wrapper, converter);
    }

    @Override
    protected boolean entityAlreadyExistsSave(CompanyFieldRequestDto requestDto) {
        return false;
    }

    @Override
    protected boolean entityAlreadyExistsUpdate(Long id, CompanyFieldRequestDto requestDto) {
        return false;
    }

    @Override
    public CompanyFieldResponseDto save(Long companyId, CompanyFieldRequestDto requestDto) {
        return null;
    }

    @Override
    public CompanyFieldResponseDto update(Long companyId, Long fieldId, CompanyFieldRequestDto requestDto) {
        return null;
    }

    @Override
    public CompanyFieldResponseDto delete(Long companyId, Long fieldId) {
        return null;
    }

    @Override
    public List<CompanyFieldResponseDto> deleteAll(Long companyId) {
        return null;
    }

    @Override
    public CompanyFieldResponseDto findById(Long companyId, Long fieldId) {
        return null;
    }

    @Override
    public List<CompanyFieldResponseDto> findAll(Long companyId) {
        return null;
    }
}

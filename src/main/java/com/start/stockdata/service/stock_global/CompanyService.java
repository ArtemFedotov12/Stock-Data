package com.start.stockdata.service.stock_global;

import com.start.stockdata.exception.exception.CompanyNotFoundException;
import com.start.stockdata.exception.exception.EntityAlreadyExistsException;
import com.start.stockdata.exception.exception.UnsupportedFieldException;
import com.start.stockdata.identity.converter.response.ResponseConverter;
import com.start.stockdata.identity.dto.request.CompanyRequestDto;
import com.start.stockdata.identity.dto.response.CompanyResponseDto;
import com.start.stockdata.identity.model.Company;
import com.start.stockdata.validations.FieldValueExists;
import com.start.stockdata.wrapper.global.CompanyWrapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService extends AbstractService<
        Company,
        CompanyRequestDto,
        CompanyResponseDto,
        CompanyWrapper> implements FieldValueExists {

    public CompanyService(CompanyWrapper wrapper, ResponseConverter<Company, CompanyResponseDto> converter) {
        super(wrapper, converter);
    }

    @Override
    public CompanyResponseDto save(CompanyRequestDto companyRequestDto) {
        if (entityAlreadyExistsSave(companyRequestDto)) {
            throw new EntityAlreadyExistsException(companyRequestDto);
        }
        return converter.toDto(wrapper.save(companyRequestDto));
    }

    public List<CompanyResponseDto> deleteAllCompanies() {
        if (this.findAll().isEmpty()) {
            throw new CompanyNotFoundException();
        }
        return convert(wrapper.deleteAllCompanies());
    }

    // Get companies by user id. Id will be taken from token
    @Override
    public List<CompanyResponseDto> findAll() {
        return convert(wrapper.findAllByUserId());
    }

    @Override
    public boolean isFieldValueExist(Object value, String fieldName) throws UnsupportedOperationException {
        if (!fieldName.equals("name")) {
            throw new UnsupportedFieldException(value.toString());
        }

        if (value == null) {
            return false;
        }
        return wrapper.findAllNameByUserId().contains(value.toString());
    }

    @Override
    protected boolean entityAlreadyExistsSave(CompanyRequestDto requestDto) {
        return false;
    }

    @Override
    protected boolean entityAlreadyExistsUpdate(final Long id, CompanyRequestDto requestDto) {
        return false;
    }

}

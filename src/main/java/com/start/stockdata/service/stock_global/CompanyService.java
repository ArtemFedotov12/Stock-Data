package com.start.stockdata.service.stock_global;

import com.start.stockdata.exception.exception.*;
import com.start.stockdata.identity.converter.request.RequestConverter;
import com.start.stockdata.identity.converter.response.ResponseConverter;
import com.start.stockdata.identity.dto.request.company.CompanyRequestDto;
import com.start.stockdata.identity.dto.response.CompanyResponseDto;
import com.start.stockdata.identity.model.Company;
import com.start.stockdata.identity.model.Field;
import com.start.stockdata.validations.FieldValueExists;
import com.start.stockdata.wrapper.global.CompanyWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.start.stockdata.util.SecurityContextUtil.getUserIdFromSecurityContext;

@Service
@Transactional
public class CompanyService implements GlobalService<
        CompanyRequestDto,
        CompanyResponseDto,
        Long>,
        FieldValueExists {

    private final CompanyWrapper wrapper;
    private final ResponseConverter<Company, CompanyResponseDto> responseConverter;
    private final RequestConverter<Company, CompanyRequestDto> requestConverter;

    public CompanyService(CompanyWrapper wrapper, ResponseConverter<Company, CompanyResponseDto> responseConverter, RequestConverter<Company, CompanyRequestDto> requestConverter) {
        this.wrapper = wrapper;
        this.responseConverter = responseConverter;
        this.requestConverter = requestConverter;
    }


    @Override
    public CompanyResponseDto save(CompanyRequestDto requestDto) {
        if (entityAlreadyExistsSave(requestDto)) {
            throw new EntityAlreadyExistsException(requestDto);
        }

        Company company = requestConverter.toEntity(requestDto);
        company.setUserId(this.getUserIdFromContext());
        wrapper.save(company);
        return responseConverter.toDto(wrapper.save(company));
    }


    @Override
    public CompanyResponseDto update(Long id, CompanyRequestDto requestDto) {
        //unchecked
        Company companyById = getCompanyById(id);
        Long userIdFromContext = this.getUserIdFromContext();

        if (!companyById.getUserId().equals(userIdFromContext)) {
            throw new CompanyNotBelongException(id);
        }

        if (entityAlreadyExistsUpdate(id, requestDto)) {
            throw new EntityAlreadyExistsException(requestDto);
        }

        Company company = requestConverter.toEntity(requestDto);
        company.setId(id);
        company.setUserId(userIdFromContext);
        return responseConverter.toDto(wrapper.update(company));
    }


    @Override
    public CompanyResponseDto findById(Long id) {
        //unchecked
        Company companyById = getCompanyById(id);
        Long userIdFromContext = this.getUserIdFromContext();

        if (!companyById.getUserId().equals(userIdFromContext)) {
            throw new CompanyNotBelongException(id);
        }

        return responseConverter.toDto(companyById);
    }


    // Get companies by user id. It will be taken from token
    @Override
    public List<CompanyResponseDto> findAll() {
        return convert(wrapper.findAll());
    }

    @Override
    public CompanyResponseDto deleteById(Long id) {
        //unchecked
        Company companyById = getCompanyById(id);
        Long userIdFromContext = this.getUserIdFromContext();

        if (!companyById.getUserId().equals(userIdFromContext)) {
            throw new CompanyNotBelongException(id);
        }
        // LazyInitializationException  exception, if after delete
        CompanyResponseDto responseDto = responseConverter.toDto(companyById);
        wrapper.deleteById(id);
        return responseDto;
    }

    public List<CompanyResponseDto> deleteAll() {
        if (wrapper.count(false) == 0) {
            throw new CompanyNotFoundException();
        }
        // LazyInitializationException exception, if after delete
        List<CompanyResponseDto> responseDtoList = convert(wrapper.findAll());
        wrapper.deleteAll();
        return responseDtoList;
    }

    public void deleteField(Long companyId, Field field) {
        Company company = getCompanyById(companyId);
        company.removeField(field);
        wrapper.save(company);
    }

    public void deleteAllFields(Long companyId) {
        Company company = getCompanyById(companyId);
        company.getFields().clear();
        wrapper.save(company);
    }

    public Field saveField(Long companyId, Field field) {
        Company company = getCompanyById(companyId);
        company.addField(field);

        Optional<Field> fieldOptional = wrapper.save(company)
                .getFields()
                .stream()
                .filter(item -> item.equals(field))
                .findFirst();

        if (fieldOptional.isPresent()) {
            return fieldOptional.get();
        } else {
            throw new SaveFieldException();
        }
    }

    public Field updateField(Long companyId, Long fieldId, Field field) {
        Company company = getCompanyById(companyId);
        field.setId(fieldId);
        company.addField(field);
        wrapper.save(company);
        return field;
    }

    @Override
    public Long count(boolean includeDeleted) {
        return wrapper.count(includeDeleted);
    }

    // Used by Validator, to check unique of the company. In our case(unique by 'name' of the company)
    public Set<String> findAllNamesByUserId() {
        return wrapper.findAllNamesByUserId(this.getUserIdFromContext());
    }

    // For Validator, to check uniqueness
    @Override
    public boolean isFieldValueExist(Object value, String fieldName) throws UnsupportedOperationException {
        if (!fieldName.equals("name")) {
            throw new UnsupportedFieldException(value.toString());
        }

        if (value == null) {
            return false;
        }

        return this
                .findAllNamesByUserId()
                .contains(value.toString());
    }


    private Company getCompanyById(Long id) {
        Optional<Company> optionalCompany = wrapper.findById(id);
        if (optionalCompany.isPresent()) {
            return optionalCompany.get();
        } else {
            throw new CompanyByIdNotFoundException(id);
        }
    }

    private List<CompanyResponseDto> convert(List<Company> entityList) {
        return entityList.stream()
                .map(responseConverter::toDto)
                .collect(Collectors.toList());
    }

    private Long getUserIdFromContext() {
        Optional<Long> optionalUserId = getUserIdFromSecurityContext();

        if (!optionalUserId.isPresent()) {
            throw new UserIdFromSecurityContextNotFoundException();
        } else {
            return optionalUserId.get();
        }

    }


    protected boolean entityAlreadyExistsSave(CompanyRequestDto requestDto) {
        return false;
    }

    protected boolean entityAlreadyExistsUpdate(final Long id, CompanyRequestDto requestDto) {
        return false;
    }

}

package com.start.stockdata.validator.attribute;


import com.start.stockdata.exception.exception.*;
import com.start.stockdata.identity.converter.request.FieldRequestConverter;
import com.start.stockdata.identity.dto.request.FieldRequestDto;
import com.start.stockdata.identity.model.Company;
import com.start.stockdata.identity.model.Field;
import com.start.stockdata.wrapper.global.company.DefaultCompanyWrapper;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Optional;
import java.util.Set;

import static com.start.stockdata.util.SecurityContextUtil.getUserIdFromSecurityContext;

@Component
public class DefaultFieldValidator implements FieldValidator<FieldRequestDto> {

    private final DefaultCompanyWrapper defaultCompanyWrapper;
    private final FieldRequestConverter requestConverter;

    public DefaultFieldValidator(DefaultCompanyWrapper defaultCompanyWrapper, FieldRequestConverter requestConverter) {
        this.defaultCompanyWrapper = defaultCompanyWrapper;
        this.requestConverter = requestConverter;
    }

    @Override
    public void validate(Long mainEntityId) {
        Optional<Company> optionalCompany = defaultCompanyWrapper.findById(mainEntityId);

        if (!optionalCompany.isPresent()) {
            throw new CompanyByIdNotFoundException(mainEntityId);
        } else {
            checkUserPossession(mainEntityId, optionalCompany.get());

        }

    }


    @Override
    public void validate(Long mainEntityId, Long id) {
        Optional<Company> optionalCompany = defaultCompanyWrapper.findById(mainEntityId);
        if (!optionalCompany.isPresent()) {
            throw new CompanyByIdNotFoundException(mainEntityId);
        } else {

            Company company = optionalCompany.get();
            checkUserPossession(mainEntityId, company);
            checkFieldWithinCompany(mainEntityId, id, company);

        }
    }

    @Override
    public void validate(Long mainEntityId, FieldRequestDto requestDto) {

        Optional<Company> optionalCompany = defaultCompanyWrapper.findById(mainEntityId);
        if (!optionalCompany.isPresent()) {
            throw new CompanyByIdNotFoundException(mainEntityId);
        } else {
            Company company = optionalCompany.get();

            checkUserPossession(mainEntityId, company);
            checkIfFieldAlreadyExist(mainEntityId, requestDto, company);

        }
    }

    @Override
    public void validate(Long mainEntityId, Long id, FieldRequestDto dto) {
        Optional<Company> optionalCompany = defaultCompanyWrapper.findById(mainEntityId);
        if (!optionalCompany.isPresent()) {
            throw new CompanyByIdNotFoundException(mainEntityId);
        } else {

            Company company = optionalCompany.get();

            checkUserPossession(mainEntityId, company);
            checkFieldWithinCompany(mainEntityId, id, company);
            // exclude itself to check on uniqueness
            checkIfFieldAlreadyExistUpdateMethod(mainEntityId, id, dto, company);

        }

    }

    private void checkIfFieldAlreadyExist(Long mainEntityId, FieldRequestDto requestDto, Company company) {
        Set<Field> fieldSet = company.getFields();
        Field field = requestConverter.toEntity(requestDto);
        boolean isFieldAlreadyExists = fieldSet
                .stream()
                .anyMatch(item -> Comparator
                        .comparing(Field::getDisplayName)
                        .compare(item, field) == 0);

        if (isFieldAlreadyExists) {
            throw new FieldAlreadyExistWithinTheCompanyException(String.valueOf(mainEntityId), requestDto);
        }
    }


    private void checkIfFieldAlreadyExistUpdateMethod(Long mainEntityId, Long id, FieldRequestDto dto, Company company) {
        Field field = requestConverter.toEntity(dto);

        boolean isFieldAlreadyExists = company.getFields()
                .stream()
                .filter(item -> !id.equals(item.getId()))
                .anyMatch(item -> Comparator
                        .comparing(Field::getDisplayName)
                        .compare(item, field) == 0);

        if (isFieldAlreadyExists) {
            throw new FieldAlreadyExistWithinTheCompanyException(String.valueOf(mainEntityId), dto);
        }
    }

    private void checkFieldWithinCompany(Long mainEntityId, Long id, Company company) {

        boolean isFieldWithinCompanyExist = company
                .getFields()
                .stream()
                .anyMatch(item -> item.getId().equals(id));

        if (!isFieldWithinCompanyExist) {
            throw new FieldWithinCompanyNotFoundException(String.valueOf(mainEntityId), String.valueOf(id));
        }

    }

    private void checkUserPossession(Long mainEntityId, Company company) {
        Long userId = company.getUserId();
        if (!userId.equals(getUserIdFromContext())) {
            throw new CompanyNotBelongException(mainEntityId);
        }
    }


    private Long getUserIdFromContext() {
        Optional<Long> optionalUserId = getUserIdFromSecurityContext();

        if (!optionalUserId.isPresent()) {
            throw new UserIdFromSecurityContextNotFoundException();
        } else {
            return optionalUserId.get();
        }
    }

}

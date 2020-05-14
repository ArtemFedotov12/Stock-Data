package com.start.stockdata.service.attribute;

import com.start.stockdata.identity.converter.request.RequestConverter;
import com.start.stockdata.identity.converter.response.ResponseConverter;
import com.start.stockdata.identity.dto.request.FieldRequestDto;
import com.start.stockdata.identity.dto.response.FieldResponseDto;
import com.start.stockdata.identity.model.Field;
import com.start.stockdata.validator.attribute.AttributeValidator;
import com.start.stockdata.validator.attribute.FieldValidator;
import com.start.stockdata.wrapper.attributes.FieldWrapper;
import com.start.stockdata.wrapper.global.CompanyWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class DefaultFieldService extends AbstractAttributeService<
        Field,
        FieldRequestDto,
        FieldResponseDto,
        FieldWrapper,
        CompanyWrapper
        > implements FieldService<FieldRequestDto, FieldResponseDto, Long> {

    private final FieldValidator<FieldRequestDto> fieldValidator;

    public DefaultFieldService(FieldWrapper attributeWrapper, CompanyWrapper mainEntityWrapper, ResponseConverter<Field, FieldResponseDto> responseConverter, RequestConverter<Field, FieldRequestDto> requestConverter, AttributeValidator<FieldRequestDto, Long> attributeValidator, FieldValidator<FieldRequestDto> fieldValidator) {
        super(attributeWrapper, mainEntityWrapper, responseConverter, requestConverter, attributeValidator);
        this.fieldValidator = fieldValidator;
    }

 /*@Override
    protected void validate(Long mainEntityId) {
        Optional<Company> optionalCompany = mainEntityWrapper.findById(mainEntityId);

        if (!optionalCompany.isPresent()) {
            throw new CompanyByIdNotFoundException(mainEntityId);
        } else {
            checkUserPossession(mainEntityId, optionalCompany.get());

        }

    }


    @Override
    protected void validate(Long mainEntityId, Long id) {
        Optional<Company> optionalCompany = mainEntityWrapper.findById(mainEntityId);
        if (!optionalCompany.isPresent()) {
            throw new CompanyByIdNotFoundException(mainEntityId);
        } else {

            Company company = optionalCompany.get();
            checkUserPossession(mainEntityId, company);
            checkFieldWithinCompany(mainEntityId, id, company);

        }
    }

    @Override
    protected void validate(Long mainEntityId, FieldRequestDto requestDto) {

        Optional<Company> optionalCompany = mainEntityWrapper.findById(mainEntityId);
        if (!optionalCompany.isPresent()) {
            throw new CompanyByIdNotFoundException(mainEntityId);
        } else {
            Company company = optionalCompany.get();

            checkUserPossession(mainEntityId, company);
            checkIfFieldAlreadyExist(mainEntityId, requestDto, company);

        }
    }

    @Override
    protected void validate(Long mainEntityId, Long id, FieldRequestDto dto) {
        Optional<Company> optionalCompany = mainEntityWrapper.findById(mainEntityId);
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
    }*/

}

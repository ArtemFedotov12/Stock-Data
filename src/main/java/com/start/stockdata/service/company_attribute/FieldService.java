package com.start.stockdata.service.company_attribute;

import com.start.stockdata.exception.exception.*;
import com.start.stockdata.identity.converter.active.ServiceConverter;
import com.start.stockdata.identity.converter.response.ResponseConverter;
import com.start.stockdata.identity.dto.request.FieldRequestDto;
import com.start.stockdata.identity.dto.response.FieldResponseDto;
import com.start.stockdata.identity.model.Company;
import com.start.stockdata.identity.model.Field;
import com.start.stockdata.wrapper.attributes.FieldWrapper;
import com.start.stockdata.wrapper.global.CompanyWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.Optional;
import java.util.Set;

import static com.start.stockdata.util.SecurityContextUtil.getUserIdFromSecurityContext;

@Transactional
@Service
public class FieldService extends AbstractAttributeService<
        Field,
        FieldRequestDto,
        FieldResponseDto,
        FieldWrapper,
        CompanyWrapper
        > {

    public FieldService(FieldWrapper attributeWrapper, CompanyWrapper mainEntityWrapper, ResponseConverter<Field, FieldResponseDto> responseConverter, ServiceConverter<Field, FieldRequestDto, FieldResponseDto> serviceConverter) {
        super(attributeWrapper, mainEntityWrapper, responseConverter, serviceConverter);
    }

    @Override
    protected void additionalCheck(Long mainEntityId, Long id) {
        Optional<Company> optionalCompany = mainEntityWrapper.findById(mainEntityId);
        if (!optionalCompany.isPresent()) {
            throw new CompanyByIdNotFoundException(mainEntityId);
        } else {
            Long userId = optionalCompany.get().getUserId();

            if (!userId.equals(getUserIdFromContext())) {
                throw new CompanyNotBelongException(mainEntityId);
            }

            Set<Field> fieldSet = optionalCompany.get().getFields();
            boolean isFieldWithSuchIdExist = fieldSet
                    .stream()
                    .anyMatch(item -> item.getId().equals(id));

            if (!isFieldWithSuchIdExist) {
                throw new FieldWithinCompanyNotFoundException(String.valueOf(mainEntityId), String.valueOf(id));
            }


        }

    }


    @Override
    protected void additionalCheck(Long mainEntityId) {
        Optional<Company> optionalCompany = mainEntityWrapper.findById(mainEntityId);

        if (!optionalCompany.isPresent()) {
            throw new CompanyByIdNotFoundException(mainEntityId);
        } else {
            Long userId = optionalCompany.get().getUserId();

            if (!userId.equals(getUserIdFromContext())) {
                throw new CompanyNotBelongException(mainEntityId);
            }

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

    @Override
    protected boolean entityAlreadyExists(Long companyId, FieldRequestDto requestDto) {

        Optional<Company> optionalCompany = mainEntityWrapper.findById(companyId);
        if (!optionalCompany.isPresent()) {
            throw new CompanyByIdNotFoundException(companyId);
        } else {
            Set<Field> fieldSet = optionalCompany.get().getFields();

            Field field = serviceConverter.toEntity(requestDto);

            return fieldSet
                    .stream()
                    .anyMatch(item -> Comparator
                            .comparing(Field::getDisplayName)
                            .compare(item, field) == 0);
        }
    }

    @Override
    protected boolean entityAlreadyExistsUpdate(Long companyId, Long id, FieldRequestDto requestDto) {

        Optional<Company> optionalCompany = mainEntityWrapper.findById(companyId);
        if (!optionalCompany.isPresent()) {
            throw new CompanyByIdNotFoundException(companyId);
        } else {
            Set<Field> fieldSet = optionalCompany.get().getFields();

            Field field = serviceConverter.toEntity(requestDto);

            return fieldSet
                    .stream()
                    .filter(item -> !id.equals(item.getId()))
                    .anyMatch(item -> Comparator
                            .comparing(Field::getDisplayName)
                            .compare(item, field) == 0);
        }
    }

/*    @Override
    protected CompanyFieldActiveDto convertToActiveDto(Long companyId, CompanyFieldRequestDto requestDto) {
        CompanyFieldActiveDto companyFieldActiveDto = serviceConverter.toActive(requestDto);
        Optional<Company> optionalCompany = mainEntityWrapper.findById(companyId);

        if (!optionalCompany.isPresent()) {
            throw new CompanyByIdNotFoundException(companyId);
        } else {
            companyFieldActiveDto.setCompany(optionalCompany.get());
            return companyFieldActiveDto;
        }
    }*/


}

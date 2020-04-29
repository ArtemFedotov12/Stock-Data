package com.start.stockdata.service.company_attribute;

import com.start.stockdata.exception.exception.CompanyByIdNotFoundException;
import com.start.stockdata.exception.exception.CompanyNotBelongException;
import com.start.stockdata.exception.exception.FieldWithinCompanyNotFoundException;
import com.start.stockdata.exception.exception.UserByIdNotFoundException;
import com.start.stockdata.identity.converter.active.ServiceConverter;
import com.start.stockdata.identity.converter.response.ResponseConverter;
import com.start.stockdata.identity.dto.active.CompanyFieldActiveDto;
import com.start.stockdata.identity.dto.request.CompanyFieldRequestDto;
import com.start.stockdata.identity.dto.response.CompanyFieldResponseDto;
import com.start.stockdata.identity.model.Company;
import com.start.stockdata.identity.model.CompanyField;
import com.start.stockdata.util.SecurityContextUtil;
import com.start.stockdata.wrapper.attributes.FieldWrapper;
import com.start.stockdata.wrapper.global.CompanyWrapper;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Optional;
import java.util.Set;

import static com.start.stockdata.util.SecurityContextUtil.getUserIdFromSecurityContext;

@Component
public class FieldService extends AbstractAttributeService<
        CompanyField,
        CompanyFieldActiveDto,
        CompanyFieldRequestDto,
        CompanyFieldResponseDto,
        FieldWrapper,
        CompanyWrapper
        > {

    public FieldService(FieldWrapper attributeWrapper, CompanyWrapper mainEntityWrapper, ResponseConverter<CompanyField, CompanyFieldResponseDto> responseConverter, ServiceConverter<CompanyFieldActiveDto, CompanyField, CompanyFieldRequestDto, CompanyFieldResponseDto> serviceConverter) {
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

            Set<CompanyField> companyFieldSet = optionalCompany.get().getCompanyFields();
            boolean isFieldWithSuchIdExist = companyFieldSet
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
            throw new UserByIdNotFoundException();
        } else {
            return optionalUserId.get();
        }
    }

    @Override
    protected boolean entityAlreadyExists(Long companyId, CompanyFieldRequestDto requestDto) {

        Optional<Company> optionalCompany = mainEntityWrapper.findById(companyId);
        if (!optionalCompany.isPresent()) {
            throw new CompanyByIdNotFoundException(companyId);
        } else {
            Set<CompanyField> fieldSet = optionalCompany.get().getCompanyFields();

            CompanyField companyField = serviceConverter.toEntity(requestDto);

            return fieldSet
                    .stream()
                    .anyMatch(item -> Comparator
                            .comparing(CompanyField::getDisplayName)
                            .compare(item, companyField) == 0);
        }
    }

    @Override
    protected boolean entityAlreadyExistsUpdate(Long companyId, Long id, CompanyFieldRequestDto requestDto) {

        Optional<Company> optionalCompany = mainEntityWrapper.findById(companyId);
        if (!optionalCompany.isPresent()) {
            throw new CompanyByIdNotFoundException(companyId);
        } else {
            Set<CompanyField> fieldSet = optionalCompany.get().getCompanyFields();

            CompanyField companyField = serviceConverter.toEntity(requestDto);

            return fieldSet
                    .stream()
                    .filter(item -> !id.equals(item.getId()))
                    .anyMatch(item -> Comparator
                            .comparing(CompanyField::getDisplayName)
                            .compare(item, companyField) == 0);
        }
    }

    @Override
    protected CompanyFieldActiveDto convertToActiveDto(Long companyId, CompanyFieldRequestDto requestDto) {
        CompanyFieldActiveDto companyFieldActiveDto = serviceConverter.toActive(requestDto);
        Optional<Company> optionalCompany = mainEntityWrapper.findById(companyId);

        if (!optionalCompany.isPresent()) {
            throw new CompanyByIdNotFoundException(companyId);
        } else {
            companyFieldActiveDto.setCompany(optionalCompany.get());
            return companyFieldActiveDto;
        }
    }
/*
    @Override
    protected boolean entityAlreadyExistsSave(Long companyId, CompanyFieldRequestDto requestDto) {
        Optional<Company> optionalCompany = companyWrapper.findById(companyId);
        if (!optionalCompany.isPresent()) {
            throw new CompanyByIdNotFoundException(companyId);
        } else {
            Set<CompanyField> fieldSet = optionalCompany.get().getCompanyFields();
            CompanyField companyField = serviceConverter.toEntity(requestDto);

            return fieldSet
                    .stream()
                    .anyMatch(item -> item.equals(companyField));
        }

    }*/


}

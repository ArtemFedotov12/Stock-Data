package com.start.stockdata.service.attribute;

import com.start.stockdata.identity.converter.response.FieldResponseConverter;
import com.start.stockdata.identity.dto.request.FieldRequestDto;
import com.start.stockdata.identity.dto.response.FieldResponseDto;
import com.start.stockdata.identity.model.Company;
import com.start.stockdata.identity.model.Field;
import com.start.stockdata.wrapper.attributes.FieldWrapper;
import com.start.stockdata.wrapper.global.CompanyWrapper;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class FieldServiceTest extends AbstractServiceTest {

    @Autowired
    private FieldService<FieldRequestDto, FieldResponseDto, Long> fieldService;
    @MockBean
    private CompanyWrapper companyWrapper;
    @MockBean
    private FieldWrapper fieldWrapper;
    @MockBean
    private FieldResponseConverter responseConverter;

    @Ignore
    @Test
    public void findById() {
        Company company = new Company();
        company.setId(5L);
        company.setName("Name");

        Field field = new Field();
        field.setId(8L);
        field.setCompany(company);
        field.setDisplayName("disp");

        FieldResponseDto responseDto = new FieldResponseDto();
        responseDto.setId(8L);
        responseDto.setDisplayName("disp");

        when(companyWrapper.findById(5L)).thenReturn(Optional.of(company));
        when(fieldWrapper.findById(8L)).thenReturn(Optional.of(field));
        when(responseConverter.toDto(field)).thenReturn(responseDto);

        FieldResponseDto actualResponseDto = fieldService.findById(5L, 8L);

        Assert.assertEquals(responseDto, actualResponseDto);

    }

}
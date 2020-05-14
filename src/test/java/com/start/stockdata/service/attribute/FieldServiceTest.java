package com.start.stockdata.service.attribute;

import com.start.stockdata.identity.converter.response.FieldResponseConverter;
import com.start.stockdata.identity.dto.request.FieldRequestDto;
import com.start.stockdata.identity.dto.response.FieldResponseDto;
import com.start.stockdata.identity.model.Company;
import com.start.stockdata.identity.model.Field;
import com.start.stockdata.validator.attribute.FieldValidator;
import com.start.stockdata.wrapper.attributes.FieldWrapper;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class FieldServiceTest extends AbstractServiceTest {

    @Autowired
    private FieldService<FieldRequestDto, FieldResponseDto, Long> fieldService;
    @MockBean
    private FieldWrapper fieldWrapper;
    @MockBean
    private FieldResponseConverter responseConverter;
    @MockBean
    FieldValidator<FieldRequestDto> fieldValidator;


    @Test
    public void findById() {
        Company company = getCompany();
        Field field = getField(company);
        FieldResponseDto responseDto = getFieldResponseDto();

        when(fieldWrapper.findById(8L)).thenReturn(Optional.of(field));
        when(responseConverter.toDto(field)).thenReturn(responseDto);

        FieldResponseDto actualResponseDto = fieldService.findById(5L, 8L);

        Assert.assertEquals(responseDto, actualResponseDto);
        verify(fieldValidator,times(1)).validate(5L,8L);
        verify(fieldWrapper,times(1)).findById(8L);
        verify(responseConverter,times(1)).toDto(field);
    }


    private FieldResponseDto getFieldResponseDto() {
        FieldResponseDto responseDto = new FieldResponseDto();
        responseDto.setId(8L);
        responseDto.setDisplayName("disp");
        return responseDto;
    }

    private Field getField(Company company) {
        Field field = new Field();
        field.setId(8L);
        field.setCompany(company);
        field.setDisplayName("disp");
        return field;
    }

    private Company getCompany() {
        Company company = new Company();
        company.setId(5L);
        company.setName("Name");
        return company;
    }

}
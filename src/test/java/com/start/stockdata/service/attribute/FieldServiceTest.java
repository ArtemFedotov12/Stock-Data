package com.start.stockdata.service.attribute;

import com.start.stockdata.identity.converter.request.RequestConverter;
import com.start.stockdata.identity.converter.response.FieldResponseConverter;
import com.start.stockdata.identity.dto.request.FieldRequestDto;
import com.start.stockdata.identity.dto.response.FieldResponseDto;
import com.start.stockdata.identity.model.Company;
import com.start.stockdata.identity.model.Field;
import com.start.stockdata.validator.attribute.FieldValidator;
import com.start.stockdata.wrapper.attributes.field.DefaultFieldWrapper;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class FieldServiceTest extends AbstractServiceTest {

    @SpyBean
    private FieldService fieldService;
    @MockBean
    private DefaultFieldWrapper defaultFieldWrapper;
    @MockBean
    private FieldResponseConverter responseConverter;
    @MockBean
    private FieldValidator<FieldRequestDto> fieldValidator;
    @MockBean
    private RequestConverter<Field, FieldRequestDto> requestConverter;


    @Test
    public void findById() {
        Company company = getCompany();//id=5L
        Field field = getField(company);
        FieldResponseDto expectedResponseDto = getFieldResponseDto();

        when(defaultFieldWrapper.findById(8L)).thenReturn(Optional.of(field));
        when(responseConverter.toDto(field)).thenReturn(expectedResponseDto);

        FieldResponseDto actualResponseDto = fieldService.findById(5L, 8L);

        Assert.assertEquals(expectedResponseDto, actualResponseDto);
        verify(fieldValidator,times(1)).validate(5L,8L);
        verify(defaultFieldWrapper,times(1)).findById(8L);
        verify(responseConverter,times(1)).toDto(field);
    }

    @Test
    public void save() {
        Company company = getCompanySave();//id=2L
        Field field = getFieldSave(company);
        FieldRequestDto fieldRequestDto = getFieldRequestDtoSave();
        FieldResponseDto expectedResponseDto = getFieldResponseDtoSave();

        when(requestConverter.toEntity(fieldRequestDto)).thenReturn(field);
        when(defaultFieldWrapper.save(2L,field)).thenReturn(field);
        when(responseConverter.toDto(field)).thenReturn(expectedResponseDto);

        FieldResponseDto actualResponseDto = fieldService.save(2L, fieldRequestDto);

        Assert.assertEquals(expectedResponseDto, actualResponseDto);

        verify(fieldValidator,times(1)).validate(2L,fieldRequestDto);
        verify(requestConverter,times(1)).toEntity(fieldRequestDto);
        //spyBean
        verify(fieldService).save(2L, fieldRequestDto);
        verify(responseConverter,times(1)).toDto(field);
    }

    private FieldResponseDto getFieldResponseDtoSave() {
        FieldResponseDto fieldResponseDto = new FieldResponseDto();
        fieldResponseDto.setAsset("A lot of employees");
        fieldResponseDto.setDisplayName("Number of employees");
        return fieldResponseDto;
    }

    private Company getCompanySave() {
        Company company = new Company();
        company.setId(2L);
        company.setName("Intel");
        return company;
    }

    private Field getFieldSave(Company company) {
        Field field = new Field();
        field.setCompany(company);
        field.setAsset("A lot of employees");
        field.setDisplayName("Number of employees");
        return field;
    }

    private FieldRequestDto getFieldRequestDtoSave() {
        FieldRequestDto requestDto = new FieldRequestDto();
        requestDto.setAsset("A lot of employees");
        requestDto.setDisplayName("Number of employees");
        return requestDto;
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
        field.setAsset("asset");
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
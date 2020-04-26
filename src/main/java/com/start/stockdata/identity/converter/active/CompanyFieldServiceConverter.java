package com.start.stockdata.identity.converter.active;

import com.start.stockdata.identity.dto.active.CompanyFieldActiveDto;
import com.start.stockdata.identity.dto.request.CompanyFieldRequestDto;
import com.start.stockdata.identity.dto.response.CompanyFieldResponseDto;
import com.start.stockdata.identity.model.CompanyField;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
public class CompanyFieldServiceConverter implements ServiceConverter<
        CompanyFieldActiveDto,
        CompanyField,
        CompanyFieldRequestDto,
        CompanyFieldResponseDto
        > {

    @Override
    public CompanyFieldActiveDto toActive(CompanyFieldRequestDto requestDto) {
        return ofNullable(requestDto)
                .map(item -> {
                    CompanyFieldActiveDto companyFieldActiveDto = new CompanyFieldActiveDto();
                    companyFieldActiveDto.setDisplayName(item.getDisplayName());
                    companyFieldActiveDto.setAsset(item.getAsset());
                    return companyFieldActiveDto;
                })
                .orElse(null);
    }

    @Override
    public CompanyField toEntity(CompanyFieldActiveDto activeDto) {
        return ofNullable(activeDto)
                .map(item -> {
                    CompanyField companyField = new CompanyField();
                    companyField.setDisplayName(item.getDisplayName());
                    companyField.setAsset(item.getAsset());
                    companyField.setShortName(item.getShortName());
                    companyField.setCompany(item.getCompany());
                    return companyField;
                })
                .orElse(null);
    }

    @Override
    public CompanyField toEntity(CompanyFieldRequestDto requestDto) {
        return ofNullable(requestDto)
                .map(item -> {
                    CompanyField companyField = new CompanyField();
                    companyField.setDisplayName(item.getDisplayName());
                    companyField.setAsset(item.getAsset());
                    companyField.setShortName(convert(item.getDisplayName()));
                    return companyField;
                })
                .orElse(null);
    }

    @Override
    public CompanyFieldResponseDto toDto(CompanyField entity) {
        return ofNullable(entity)
                .map(item -> {
                    CompanyFieldResponseDto responseDto = new CompanyFieldResponseDto();
                    responseDto.setDisplayName(item.getDisplayName());
                    responseDto.setAsset(item.getAsset());
                    return responseDto;
                })
                .orElse(null);
    }

    private String convert(String userLine) {
        StringBuilder result = new StringBuilder();
        String[] arr = userLine.trim().replaceAll(" +", " ").split(" ");
        result.append(arr[0].toLowerCase());
        for (int i = 1; i < arr.length-1; i++) {
            result.append(Character.toString(arr[i].charAt(0)).toUpperCase());
            if(arr[i].length()>1) {
                result.append(arr[i].substring(1).toLowerCase());
            }
        }

        return result.toString();
    }

}

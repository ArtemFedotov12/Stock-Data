package com.start.stockdata.rest.controller.global.user;

import com.google.gson.Gson;
import com.start.stockdata.identity.dto.request.company.CompanyRequestDto;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.*;

import static com.start.stockdata.utils.TestConstants.SAVE_SUCCESS_REQUEST_DTO_JSON;

public class Mains {
    public static void main(String[] args) throws IOException {
        Gson gson = new Gson();
        File file = new File(SAVE_SUCCESS_REQUEST_DTO_JSON);
        String json = FileUtils.readFileToString(file, String.valueOf(StandardCharsets.UTF_8));
        CompanyRequestDto companyRequestDto = gson.fromJson(json,CompanyRequestDto.class);
        System.out.println(file.exists());
    }
}

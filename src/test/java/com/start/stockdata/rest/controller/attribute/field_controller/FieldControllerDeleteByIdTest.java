package com.start.stockdata.rest.controller.attribute.field_controller;

import com.start.stockdata.identity.dto.response.FieldResponseDto;
import com.start.stockdata.rest.controller.AbstractIntegrationTest;
import com.start.stockdata.rest.controller.dto.ErrorDto;
import com.start.stockdata.wrapper.attributes.field.FieldWrapper;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MvcResult;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static com.start.stockdata.utils.SecurityTestUtils.getAdmin;
import static com.start.stockdata.utils.SecurityTestUtils.initSecurityContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class FieldControllerDeleteByIdTest extends AbstractIntegrationTest {

    @Autowired
    private FieldWrapper fieldWrapper;

    @Sql(value = {"/sql/field_controller/delete_by_id/success/init-db.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/sql/field_controller/delete_by_id/success/clear-db.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void deleteByIdSuccess200() throws Exception {

        FieldResponseDto expectedResponseDto = getSuccessResponseDto();

        final MvcResult mvcResult = this.mockMvc
                .perform(delete("/companies/10/fields/5")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                        //userId=1
                        .with(initSecurityContext(getAdmin()))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        final FieldResponseDto actualResponseDto =
                gson.fromJson(mvcResult.getResponse().getContentAsString(), FieldResponseDto.class);

        Assert.assertFalse(fieldWrapper.findById(5L).isPresent());
        Assert.assertEquals(expectedResponseDto, actualResponseDto);
        Assert.assertEquals(new Long(5), actualResponseDto.getId());

    }

    @Test
    public void deleteByIdUnauthorized401() throws Exception {
        this.mockMvc
                .perform(delete("/companies/10/fields/5")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void deleteByIdCompanyNotExist400() throws Exception {
        final ErrorDto expectedResponseDto = getCompanyNotExistResponseDto();

        final MvcResult mvcResult = this.mockMvc
                .perform(delete("/companies/200/fields/6")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                        //userId=1
                        .with(initSecurityContext(getAdmin()))
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        final ErrorDto actualResponseDto =
                gson.fromJson(mvcResult.getResponse().getContentAsString(), ErrorDto.class);

        Assert.assertEquals(expectedResponseDto, actualResponseDto);
    }


    @Sql(value = {"/sql/field_controller/delete_by_id/company_not_belong_to_current_user/init-db.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/sql/field_controller/delete_by_id/company_not_belong_to_current_user/clear-db.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void deleteByIdCompanyNotBelongToCurrentUser400() throws Exception {

        final ErrorDto expectedResponseDto = getCompanyNotBelongToCurrentUserResponseDto();

        final MvcResult mvcResult = this.mockMvc
                //user with userId=2 has company with companyId=25 and fieldId=6
                .perform(delete("/companies/25/fields/6")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                        //userId=1 ---- current user
                        .with(initSecurityContext(getAdmin()))
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        final ErrorDto mvcResultResponseDto =
                gson.fromJson(mvcResult.getResponse().getContentAsString(), ErrorDto.class);

        Assert.assertEquals(expectedResponseDto, mvcResultResponseDto);
    }


    @Sql(value = {"/sql/field_controller/delete_by_id/field_not_belong_to_company/init-db.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/sql/field_controller/delete_by_id/field_not_belong_to_company/clear-db.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void deleteByIdFieldNotBelongToCompany400() throws Exception {
        final ErrorDto expectedResponseDto = getFieldNotBelongToCompanyResponseDto();

        final MvcResult mvcResult = this.mockMvc
                 //user with userId=2 has companyId=18, and this company has fieldId=6,
                 //so company with companyId=17 DOESN'T have fieldId=6
                .perform(delete("/companies/17/fields/6")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                        //userId=1;
                        .with(initSecurityContext(getAdmin()))
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        final ErrorDto actualResponseDto =
                gson.fromJson(mvcResult.getResponse().getContentAsString(), ErrorDto.class);

        Assert.assertEquals(expectedResponseDto, actualResponseDto);

    }

    @Sql(value = {"/sql/field_controller/delete_by_id/field_not_exist_in_db/init-db.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/sql/field_controller/delete_by_id/field_not_exist_in_db/clear-db.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void deleteByIdFieldNotExistInDB400() throws Exception {
        final ErrorDto expectedResponseDto = getFieldNotExistInDBResponseDto();

        final MvcResult mvcResult = this.mockMvc
                .perform(delete("/companies/7/fields/600")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                        .with(initSecurityContext(getAdmin()))
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        final ErrorDto actualResponseDto =
                gson.fromJson(mvcResult.getResponse().getContentAsString(), ErrorDto.class);

        Assert.assertFalse(fieldWrapper.findById(600L).isPresent());
        Assert.assertEquals(expectedResponseDto, actualResponseDto);
    }

    private ErrorDto getFieldNotExistInDBResponseDto() throws IOException {
        File file = new File("src/test/resources/json/field_controller/delete_by_id/field_not_exist_in_db/response-dto.json");
        String json = FileUtils.readFileToString(file, String.valueOf(StandardCharsets.UTF_8));
        return gson.fromJson(json, ErrorDto.class);
    }

    private ErrorDto getFieldNotBelongToCompanyResponseDto() throws IOException {
        File file = new File("src/test/resources/json/field_controller/delete_by_id/field_not_belong_to_company/response-dto.json");
        String json = FileUtils.readFileToString(file, String.valueOf(StandardCharsets.UTF_8));
        return gson.fromJson(json, ErrorDto.class);
    }

    private ErrorDto getCompanyNotBelongToCurrentUserResponseDto() throws IOException {
        File file = new File("src/test/resources/json/field_controller/delete_by_id/company_not_belong_to_user/response-dto.json");
        String json = FileUtils.readFileToString(file, String.valueOf(StandardCharsets.UTF_8));
        return gson.fromJson(json, ErrorDto.class);
    }

    private ErrorDto getCompanyNotExistResponseDto() throws IOException {
        File file = new File("src/test/resources/json/field_controller/delete_by_id/company_not_exist/response-dto.json");
        String json = FileUtils.readFileToString(file, String.valueOf(StandardCharsets.UTF_8));
        return gson.fromJson(json, ErrorDto.class);
    }


    private FieldResponseDto getSuccessResponseDto() throws IOException {
        File file = new File("src/test/resources/json/field_controller/delete_by_id/success/response-dto.json");
        String json = FileUtils.readFileToString(file, String.valueOf(StandardCharsets.UTF_8));
        return gson.fromJson(json, FieldResponseDto.class);
    }
}

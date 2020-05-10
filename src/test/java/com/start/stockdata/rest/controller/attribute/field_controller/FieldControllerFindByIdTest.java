package com.start.stockdata.rest.controller.attribute.field_controller;

import com.start.stockdata.identity.converter.request.FieldRequestConverter;
import com.start.stockdata.identity.converter.response.FieldResponseConverter;
import com.start.stockdata.identity.dto.response.FieldResponseDto;
import com.start.stockdata.rest.controller.AbstractIntegrationTest;
import com.start.stockdata.rest.controller.dto.ErrorDto;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class FieldControllerFindByIdTest extends AbstractIntegrationTest {

    @Autowired
    FieldRequestConverter fieldRequestConverter;
    @Autowired
    FieldResponseConverter fieldResponseConverter;
    


    @Sql(value = {"/sql/field_controller/find_by_id/success/init-db.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/sql/field_controller/find_by_id/success/clear-db.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void findByIdSuccess200() throws Exception {
        final FieldResponseDto expectedResponseDto = getSuccessResponseDto();

        //userId=1, companyId=7, fieldId=5
        final MvcResult mvcResult = this.mockMvc
                .perform(
                        get("/companies/7/fields/5")
                                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                                .with(initSecurityContext(getAdmin())
                                ))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        FieldResponseDto mvcResultResponseDto =
                gson.fromJson(mvcResult.getResponse().getContentAsString(), FieldResponseDto.class);

        Assert.assertEquals(expectedResponseDto, mvcResultResponseDto);
        Assert.assertEquals(new Long(5), mvcResultResponseDto.getId());
    }

  /*
    user with id=1 has company with id=7;
    user with id=2 has company with id=8;
     field with id=5, belong to companyId=7;
    field with id=6, belong to companyId=8;
    */
    @Sql(value = {"/sql/field_controller/find_by_id/field_not_belong_to_company/init-db.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/sql/field_controller/find_by_id/field_not_belong_to_company/clear-db.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void findByIdFieldNotBelongToSpecifiedCompany400() throws Exception {
       final ErrorDto expectedResponseDto = getFieldNotBelongToSpecifiedCompanyResponseDto();

        final MvcResult mvcResult = this.mockMvc
                //field with id=6, belongs to userId=2 and companyId=8
                .perform(get("/companies/7/fields/6")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                        //userId=1;
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

    @Sql(value = {"/sql/field_controller/find_by_id/field_not_exist_in_db/init-db.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/sql/field_controller/find_by_id/field_not_exist_in_db/clear-db.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void findByIdFieldNotExistInDB400() throws Exception {
        final ErrorDto expectedResponseDto = getFieldNotExistInDBResponseDto();

        final MvcResult mvcResult = this.mockMvc
                .perform(get("/companies/7/fields/600")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
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


    /*
    user with id=1, has company with companyId=15
    user with id=2, has company with companyId=25
    * */
    @Sql(value = {"/sql/field_controller/find_by_id/company_not_belong_to_current_user/init-db.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/sql/field_controller/find_by_id/company_not_belong_to_current_user/clear-db.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void findByIdCompanyNotBelongToCurrentUser400() throws Exception {

        final ErrorDto expectedResponseDto = getCompanyNotBelongToCurrentUserResponseDto();

        final MvcResult mvcResult = this.mockMvc
                .perform(get("/companies/25/fields/6")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                        //userId=1
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

    @Test
    public void findByIdCompanyNotExist400() throws Exception {
        final ErrorDto expectedResponseDto = getCompanyNotExistResponseDto();

        final MvcResult mvcResult = this.mockMvc
                .perform(get("/companies/367/fields/6")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                        //userId=1
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

    @Test
    public void findByIdUnauthorized401() throws Exception {

         this.mockMvc
                .perform(get("/companies/20/fields/6")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    private ErrorDto getCompanyNotExistResponseDto() throws IOException {
        File file = new File("src/test/resources/json/field_controller/findById/company_not_exist/response-dto.json");
        String json = FileUtils.readFileToString(file, String.valueOf(StandardCharsets.UTF_8));
        return gson.fromJson(json, ErrorDto.class);
    }

    private ErrorDto getCompanyNotBelongToCurrentUserResponseDto() throws IOException {
        File file = new File("src/test/resources/json/field_controller/findById/company_not_belong_to_user/response-dto.json");
        String json = FileUtils.readFileToString(file, String.valueOf(StandardCharsets.UTF_8));
        return gson.fromJson(json, ErrorDto.class);
    }

    private ErrorDto getFieldNotExistInDBResponseDto() throws IOException {
        File file = new File("src/test/resources/json/field_controller/findById/field_not_exist_in_db/response-dto.json");
        String json = FileUtils.readFileToString(file, String.valueOf(StandardCharsets.UTF_8));
        return gson.fromJson(json, ErrorDto.class);
    }

    private ErrorDto getFieldNotBelongToSpecifiedCompanyResponseDto() throws IOException {
        File file = new File("src/test/resources/json/field_controller/findById/field_not_belong_to_company/response-dto.json");
        String json = FileUtils.readFileToString(file, String.valueOf(StandardCharsets.UTF_8));
        return gson.fromJson(json, ErrorDto.class);
    }




    private FieldResponseDto getSuccessResponseDto() throws IOException {
        File file = new File("src/test/resources/json/field_controller/findById/success/response-dto.json");
        String json = FileUtils.readFileToString(file, String.valueOf(StandardCharsets.UTF_8));
        return gson.fromJson(json, FieldResponseDto.class);
    }

}

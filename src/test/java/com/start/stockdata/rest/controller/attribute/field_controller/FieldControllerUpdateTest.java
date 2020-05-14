package com.start.stockdata.rest.controller.attribute.field_controller;

import com.start.stockdata.identity.dto.request.FieldRequestDto;
import com.start.stockdata.identity.dto.response.FieldResponseDto;
import com.start.stockdata.rest.controller.AbstractIntegrationTest;
import com.start.stockdata.rest.controller.dto.ErrorDto;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class FieldControllerUpdateTest extends AbstractIntegrationTest {

    @Sql(value = {"/sql/field_controller/update/success/init-db.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/sql/field_controller/update/success/clear-db.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void update200() throws Exception {

        final FieldRequestDto requestDto = getSuccessRequestDto();
        final FieldResponseDto expectedResponseDto = getSuccessResponseDto();

        final MvcResult mvcResult = this.mockMvc
                .perform(put("/companies/10/fields/18")
                        .content(gson.toJson(requestDto))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                        .with(initSecurityContext(getAdmin()))
                )
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        FieldResponseDto mvcResultResponseDto =
                gson.fromJson(mvcResult.getResponse().getContentAsString(), FieldResponseDto.class);

        Assert.assertEquals(expectedResponseDto, mvcResultResponseDto);
        // id must be generated for new Field
        Assert.assertNotNull(expectedResponseDto.getId());
    }

    @Sql(value = {"/sql/field_controller/update/same_data/init-db.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/sql/field_controller/update/same_data/clear-db.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void updateTheSameData200() throws Exception{
        final FieldRequestDto requestDto = getTheSameDataRequestDto();
        final FieldResponseDto expectedResponseDto = getTheSameDataResponseDto();

        final MvcResult mvcResult = this.mockMvc
                .perform(put("/companies/10/fields/18")
                        .content(gson.toJson(requestDto))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                        .with(initSecurityContext(getAdmin()))
                )
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        FieldResponseDto mvcResultResponseDto =
                gson.fromJson(mvcResult.getResponse().getContentAsString(), FieldResponseDto.class);

        Assert.assertEquals(expectedResponseDto, mvcResultResponseDto);
        // id must be generated for new Field
        Assert.assertNotNull(expectedResponseDto.getId());
    }

    @Sql(value = {"/sql/field_controller/update/already_exist/init-db.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/sql/field_controller/update/already_exist/clear-db.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void updateAlreadyExists400() throws Exception {
        final FieldRequestDto requestDto = getAlreadyExistRequestDto();
        final ErrorDto expectedResponseDto = getAlreadyExistResponseDto();

        final MvcResult mvcResult = this.mockMvc
                .perform(put("/companies/10/fields/19")
                        .content(gson.toJson(requestDto))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                        .with(initSecurityContext(getAdmin()))
                )
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();

        ErrorDto mvcResultResponseDto =
                gson.fromJson(mvcResult.getResponse().getContentAsString(), ErrorDto.class);

        Assert.assertEquals(expectedResponseDto, mvcResultResponseDto);
    }

    @Sql(value = {"/sql/field_controller/update/company_not_belong_to_current_user/init-db.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/sql/field_controller/update/company_not_belong_to_current_user/clear-db.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void updateCompanyNotBelongToCurrentUser400() throws Exception {

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

    @Sql(value = {"/sql/field_controller/update/field_not_exist/init-db.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/sql/field_controller/update/field_not_exist/clear-db.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void updateFieldNotExist400() throws Exception {

        final FieldRequestDto requestDto = getFieldNotExistRequestDto();
        final ErrorDto expectedResponseDto = getCompanyFieldNotExistResponseDto();

        final MvcResult mvcResult = this.mockMvc
                .perform(get("/companies/10/fields/123")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                        .content(gson.toJson(requestDto))
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


    @Sql(value = {"/sql/field_controller/update/field_not_belong_to_user/init-db.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/sql/field_controller/update/field_not_belong_to_user/clear-db.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void updateFieldNotBelongToCurrentUser400() throws Exception {

        final FieldRequestDto requestDto = getFieldNotBelongToCurrentUserRequestDto();
        final ErrorDto expectedResponseDto = getFieldNotBelongToCurrentUserResponseDto();

        final MvcResult mvcResult = this.mockMvc
                .perform(get("/companies/10/fields/21")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                        .content(gson.toJson(requestDto))
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

    private FieldRequestDto getFieldNotBelongToCurrentUserRequestDto() throws IOException {
        File file = new File("src/test/resources/json/field_controller/update/field_not_belong_to_user/request-dto.json");
        String json = FileUtils.readFileToString(file, String.valueOf(StandardCharsets.UTF_8));
        return gson.fromJson(json, FieldRequestDto.class);
    }

    private ErrorDto getFieldNotBelongToCurrentUserResponseDto() throws IOException {
        File file = new File("src/test/resources/json/field_controller/update/field_not_belong_to_user/response-dto.json");
        String json = FileUtils.readFileToString(file, String.valueOf(StandardCharsets.UTF_8));
        return gson.fromJson(json, ErrorDto.class);
    }

    private FieldRequestDto getFieldNotExistRequestDto() throws IOException {
        File file = new File("src/test/resources/json/field_controller/update/field_not_exist/request-dto.json");
        String json = FileUtils.readFileToString(file, String.valueOf(StandardCharsets.UTF_8));
        return gson.fromJson(json, FieldRequestDto.class);
    }

    private ErrorDto getCompanyFieldNotExistResponseDto() throws IOException {
        File file = new File("src/test/resources/json/field_controller/update/field_not_exist/response-dto.json");
        String json = FileUtils.readFileToString(file, String.valueOf(StandardCharsets.UTF_8));
        return gson.fromJson(json, ErrorDto.class);
    }

    private ErrorDto getCompanyNotBelongToCurrentUserResponseDto() throws IOException {
        File file = new File("src/test/resources/json/field_controller/update/company_not_belong_to_user/response-dto.json");
        String json = FileUtils.readFileToString(file, String.valueOf(StandardCharsets.UTF_8));
        return gson.fromJson(json, ErrorDto.class);
    }

    private FieldRequestDto getAlreadyExistRequestDto() throws IOException {
        File file = new File("src/test/resources/json/field_controller/update/already_exist/request-dto.json");
        String json = FileUtils.readFileToString(file, String.valueOf(StandardCharsets.UTF_8));
        return gson.fromJson(json, FieldRequestDto.class);
    }

    private ErrorDto getAlreadyExistResponseDto() throws IOException {
        File file = new File("src/test/resources/json/field_controller/update/already_exist/response-dto.json");
        String json = FileUtils.readFileToString(file, String.valueOf(StandardCharsets.UTF_8));
        return gson.fromJson(json, ErrorDto.class);
    }

    private FieldResponseDto getTheSameDataResponseDto() throws IOException {
        File file = new File("src/test/resources/json/field_controller/update/same_data/response-dto.json");
        String json = FileUtils.readFileToString(file, String.valueOf(StandardCharsets.UTF_8));
        return gson.fromJson(json, FieldResponseDto.class);
    }

    private FieldRequestDto getTheSameDataRequestDto() throws IOException {
        File file = new File("src/test/resources/json/field_controller/update/same_data/request-dto.json");
        String json = FileUtils.readFileToString(file, String.valueOf(StandardCharsets.UTF_8));
        return gson.fromJson(json, FieldRequestDto.class);
    }

    private FieldResponseDto getSuccessResponseDto() throws IOException {
        File file = new File("src/test/resources/json/field_controller/update/success/response-dto.json");
        String json = FileUtils.readFileToString(file, String.valueOf(StandardCharsets.UTF_8));
        return gson.fromJson(json, FieldResponseDto.class);
    }

    private FieldRequestDto getSuccessRequestDto() throws IOException {
        File file = new File("src/test/resources/json/field_controller/update/success/request-dto.json");
        String json = FileUtils.readFileToString(file, String.valueOf(StandardCharsets.UTF_8));
        return gson.fromJson(json, FieldRequestDto.class);
    }

}

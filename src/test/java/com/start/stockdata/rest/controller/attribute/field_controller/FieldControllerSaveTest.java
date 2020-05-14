package com.start.stockdata.rest.controller.attribute.field_controller;

import com.start.stockdata.identity.converter.request.FieldRequestConverter;
import com.start.stockdata.identity.converter.response.FieldResponseConverter;
import com.start.stockdata.identity.dto.request.FieldRequestDto;
import com.start.stockdata.identity.dto.response.FieldResponseDto;
import com.start.stockdata.identity.model.Field;
import com.start.stockdata.rest.controller.AbstractIntegrationTest;
import com.start.stockdata.rest.controller.dto.ErrorDto;
import com.start.stockdata.wrapper.attributes.FieldWrapper;
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

import static com.start.stockdata.utils.SecurityTestUtils.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class FieldControllerSaveTest extends AbstractIntegrationTest {

    @Autowired
    FieldWrapper fieldWrapper;
    @Autowired
    FieldRequestConverter fieldRequestConverter;
    @Autowired
    FieldResponseConverter fieldResponseConverter;


    @Sql(value = {"/sql/field_controller/save/success/init-db.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/sql/field_controller/save/success/clear-db.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void saveSuccess200() throws Exception {

        //final String TOKEN = getToken(EMAIL_ADMIN, PASSWORD_ADMIN);
        final FieldRequestDto requestDto = getFieldSuccessRequestDto();
        final FieldResponseDto expectedResponseDto = getFieldSuccessResponseDto();


        final MvcResult mvcResult = this.mockMvc
                .perform(
                        post("/companies/1/fields")
                                .content(gson.toJson(requestDto))
                                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                                /* instead of getting real token and then parse it ,
                                   and put in SecurityContextHolder(details) then,
                                   we use 'initSecurityContext' method.
                                   In this way we can control userId for our sql scripts
                                   and another details
                                 */
                                .with(initSecurityContext(getAdmin()))
                        //.header(API_TOKEN, BEARER_WITH_SPACE + TOKEN)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        FieldResponseDto mvcResultResponseDto =
                gson.fromJson(mvcResult.getResponse().getContentAsString(), FieldResponseDto.class);

        // Maybe it is redundant. For integration tests
        Field fieldFormDb = fieldWrapper.findById(mvcResultResponseDto.getId()).get();

        // check response with actual entity in db(if it really has been saved to db)
        Assert.assertEquals(fieldResponseConverter.toDto(fieldFormDb), mvcResultResponseDto);
        // id, removalDate excluded from (equals and hashcode)
        Assert.assertEquals(expectedResponseDto, mvcResultResponseDto);
        // id must be generated for new Field
        Assert.assertNotNull(expectedResponseDto.getId());
    }

    @Test
    public void saveUnauthorized401() throws Exception {

        final FieldRequestDto requestDto = getFieldSuccessRequestDto();

        this.mockMvc
                .perform(post("/companies/1/fields")
                        .content(gson.toJson(requestDto))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    //company with id=1 belong to user with also id=1
    @Sql(value = {"/sql/field_controller/save/not_belong/init-db.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/sql/field_controller/save/not_belong/clear-db.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void saveCompanyNotBelongException400() throws Exception {
        final FieldRequestDto requestDto = getFieldNotBelongRequestDto();
        final ErrorDto expectedResponseDto = getFieldErrorResponseDto();

        final MvcResult mvcResult = this.mockMvc
                .perform(post("/companies/7/fields")
                                .content(gson.toJson(requestDto))
                                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                                //this user doesn't have company with id=7
                                .with(initSecurityContext(getUser()))
                        //.header(API_TOKEN, BEARER_WITH_SPACE + TOKEN)
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        final ErrorDto mcvResultResponseDto
                = gson.fromJson(mvcResult.getResponse().getContentAsString(), ErrorDto.class);

        Assert.assertEquals(expectedResponseDto, mcvResultResponseDto);
    }


    //userId =1 , companyId = 7
    @Sql(value = {"/sql/field_controller/save/duplicated_field/init-db.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/sql/field_controller/save/duplicated_field/clear-db.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void saveDuplicatedField400() throws Exception {
        final FieldRequestDto requestDto = getFieldDuplicateRequestDto();
        final ErrorDto expectedResponseDto = getFieldDuplicateErrorResponseDto();

        final MvcResult mvcResult = this.mockMvc
                .perform(post("/companies/7/fields")
                        .content(gson.toJson(requestDto))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                        .with(initSecurityContext(getAdmin()))
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        final ErrorDto mcvResultResponseDto
                = gson.fromJson(mvcResult.getResponse().getContentAsString(), ErrorDto.class);

        Assert.assertEquals(expectedResponseDto, mcvResultResponseDto);

    }

    @Test
    public void saveCompanyNotExist400() throws Exception {
        final FieldRequestDto requestDto = getFieldCompanyNotExistRequestDto();
        final ErrorDto expectedResponseDto = getFieldCompanyNotExistResponseDto();

        final MvcResult mvcResult = this.mockMvc
                .perform(post("/companies/499/fields")
                        .content(gson.toJson(requestDto))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                        .with(initSecurityContext(getAdmin()))
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        final ErrorDto mcvResultResponseDto
                = gson.fromJson(mvcResult.getResponse().getContentAsString(), ErrorDto.class);

        Assert.assertEquals(expectedResponseDto, mcvResultResponseDto);
    }

    private FieldRequestDto getFieldCompanyNotExistRequestDto() throws IOException {
        File file = new File("src/test/resources/json/field_controller/save/company_not_exist/request-dto.json");
        String json = FileUtils.readFileToString(file, String.valueOf(StandardCharsets.UTF_8));
        return gson.fromJson(json, FieldRequestDto.class);
    }

    private ErrorDto getFieldCompanyNotExistResponseDto() throws IOException {
        File file = new File("src/test/resources/json/field_controller/save/company_not_exist/response-dto.json");
        String json = FileUtils.readFileToString(file, String.valueOf(StandardCharsets.UTF_8));
        return gson.fromJson(json, ErrorDto.class);
    }


    private FieldRequestDto getFieldDuplicateRequestDto() throws IOException {
        File file = new File("src/test/resources/json/field_controller/save/duplicated_field/request-dto.json");
        String json = FileUtils.readFileToString(file, String.valueOf(StandardCharsets.UTF_8));
        return gson.fromJson(json, FieldRequestDto.class);
    }

    private ErrorDto getFieldDuplicateErrorResponseDto() throws IOException {
        File file = new File("src/test/resources/json/field_controller/save/duplicated_field/response-dto.json");
        String json = FileUtils.readFileToString(file, String.valueOf(StandardCharsets.UTF_8));
        return gson.fromJson(json, ErrorDto.class);
    }

    private FieldRequestDto getFieldSuccessRequestDto() throws IOException {
        File file = new File("src/test/resources/json/field_controller/save/success/request-dto.json");
        String json = FileUtils.readFileToString(file, String.valueOf(StandardCharsets.UTF_8));
        return gson.fromJson(json, FieldRequestDto.class);
    }

    private FieldResponseDto getFieldSuccessResponseDto() throws IOException {
        File file = new File("src/test/resources/json/field_controller/save/success/response-dto.json");
        String json = FileUtils.readFileToString(file, String.valueOf(StandardCharsets.UTF_8));
        return gson.fromJson(json, FieldResponseDto.class);
    }

    private FieldRequestDto getFieldNotBelongRequestDto() throws IOException {
        File file = new File("src/test/resources/json/field_controller/save/not_belong/request-dto.json");
        String json = FileUtils.readFileToString(file, String.valueOf(StandardCharsets.UTF_8));
        return gson.fromJson(json, FieldRequestDto.class);
    }

    private ErrorDto getFieldErrorResponseDto() throws IOException {
        File file = new File("src/test/resources/json/field_controller/save/not_belong/response-dto.json");
        String json = FileUtils.readFileToString(file, String.valueOf(StandardCharsets.UTF_8));
        return gson.fromJson(json, ErrorDto.class);
    }


}
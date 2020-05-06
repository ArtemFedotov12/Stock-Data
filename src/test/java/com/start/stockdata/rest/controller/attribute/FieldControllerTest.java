package com.start.stockdata.rest.controller.attribute;

import com.start.stockdata.identity.converter.active.Converter;
import com.start.stockdata.identity.dto.request.FieldRequestDto;
import com.start.stockdata.identity.dto.response.FieldResponseDto;
import com.start.stockdata.identity.model.Field;
import com.start.stockdata.rest.controller.AbstractIntegrationTest;
import com.start.stockdata.wrapper.attributes.FieldWrapper;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static com.start.stockdata.util.constants.GlobalConstants.API_TOKEN;
import static com.start.stockdata.util.constants.GlobalConstants.BEARER_WITH_SPACE;
import static com.start.stockdata.utils.TokenGenerator.getToken;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class FieldControllerTest extends AbstractIntegrationTest {

    //admin role
    public static final String EMAIL_ADMIN = "vlad.danylovych@gmail.com";
    public static final String PASSWORD_ADMIN = "password";

    public static final String EMAIL_USER = "justuser@gmail.com";
    public static final String PASSWORD_USER = "password";

    @Autowired
    FieldWrapper fieldWrapper;
    @Autowired
    Converter<Field, FieldRequestDto, FieldResponseDto> converter;



    @Sql(value = {"/sql/field_controller/save_success/init.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/sql/field_controller/save_success/clear-db.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    void saveSuccess200() throws Exception {

        final String TOKEN = getToken(EMAIL_ADMIN, PASSWORD_ADMIN);
        final FieldRequestDto requestDto = getFieldRequestDto();
        final FieldResponseDto expectedResponseDto = getResponseDto();

        final MvcResult mvcResult = this.mockMvc
                .perform(post("/companies/1/fields")
                        .content(gson.toJson(requestDto))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                        .header(API_TOKEN, BEARER_WITH_SPACE + TOKEN))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        FieldResponseDto mvcResultResponseDto =
                gson.fromJson(mvcResult.getResponse().getContentAsString(), FieldResponseDto.class);

        Field fieldFormDb = fieldWrapper.findById(mvcResultResponseDto.getId()).get();

        // check response with actual entity in db(if it really has been saved to db)
        Assert.assertEquals(converter.toDto(fieldFormDb), mvcResultResponseDto);
        // id, removalDate excluded from (equals and hashcode)
        Assert.assertEquals(expectedResponseDto, mvcResultResponseDto);
        // id must be generated for new Field
        Assert.assertNotNull(expectedResponseDto.getId());
    }

    @Test
    void saveUnauthorized401() throws Exception {
        final FieldRequestDto requestDto = getFieldRequestDto();

        this.mockMvc
                .perform(post("/companies/1/fields")
                        .content(gson.toJson(requestDto))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    void saveCompanyNotBelong() throws Exception {
        final FieldRequestDto requestDto = getFieldRequestDto();
    }


    private FieldRequestDto getFieldRequestDto() throws IOException {
        File file = new File("src/test/resources/controller-json/field_controller/save-success-request-dto.json");
        String json = FileUtils.readFileToString(file, String.valueOf(StandardCharsets.UTF_8));
        return gson.fromJson(json, FieldRequestDto.class);
    }

    private FieldResponseDto getResponseDto() throws IOException {
        File file = new File("src/test/resources/controller-json/field_controller/save-success-response-dto.json");
        String json = FileUtils.readFileToString(file, String.valueOf(StandardCharsets.UTF_8));
        return gson.fromJson(json, FieldResponseDto.class);
    }


}
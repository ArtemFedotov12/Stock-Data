package com.start.stockdata.rest.controller.global.user;

import com.start.stockdata.identity.dto.request.company.CompanyRequestDto;
import com.start.stockdata.identity.dto.response.CompanyResponseDto;
import com.start.stockdata.rest.controller.AbstractIntegrationTest;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
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
import java.util.Objects;

import static com.start.stockdata.util.constants.GlobalConstants.API_TOKEN;
import static com.start.stockdata.util.constants.GlobalConstants.BEARER_WITH_SPACE;
import static com.start.stockdata.util.constants.UriPath.COMPANIES_PATH;
import static com.start.stockdata.utils.TestConstants.*;
import static com.start.stockdata.utils.TokenGenerator.getToken;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CompanyControllerTest extends AbstractIntegrationTest {

    private static final String EMAIL = "vlad.danylovych@gmail.com";
    private static final String PASSWORD = "password";


    @Sql(value = {"/sql/company_controller/save_success/init-db.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/sql/company_controller/save_success/clear-db.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void save_success() throws Exception {

        final String TOKEN = getToken(EMAIL, PASSWORD);
        CompanyRequestDto companyRequestDto = getCompanyRequestDto("src/test/resources/controller-json/company_controller/save-success-request-dto.json");
        CompanyResponseDto expectedResponseDto = getCompanyResponseDto("src/test/resources/controller-json/company_controller/save-success-response-dto.json");

        final MvcResult mvcResult = this.mockMvc
                .perform(post(COMPANIES_PATH)
                        .content(gson.toJson(companyRequestDto))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                        .header(API_TOKEN, BEARER_WITH_SPACE + TOKEN))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        CompanyResponseDto mvcResultResponseDto =
                gson.fromJson(mvcResult.getResponse().getContentAsString(), CompanyResponseDto.class);

        // id, removalDate excluded from (equals and hashcode)
        Assert.assertEquals(expectedResponseDto, mvcResultResponseDto);

        Assert.assertNotNull(expectedResponseDto.getId());

        Assert.assertTrue(expectedResponseDto
                .getFields()
                .stream()
                .allMatch(item -> Objects.nonNull(item.getId())));

        Assert.assertTrue(expectedResponseDto
                .getFactors()
                .stream()
                .allMatch(item -> Objects.nonNull(item.getId())));

        Assert.assertTrue(expectedResponseDto
                .getTypes()
                .stream()
                .allMatch(item -> Objects.nonNull(item.getId())));

    }

    private CompanyRequestDto getCompanyRequestDto(String path) throws IOException {
        File file = new File(path);
        String json = FileUtils.readFileToString(file, String.valueOf(StandardCharsets.UTF_8));
        return gson.fromJson(json, CompanyRequestDto.class);
    }

    private CompanyResponseDto getCompanyResponseDto(String path) throws IOException {
        File file = new File(path);
        String json = FileUtils.readFileToString(file, String.valueOf(StandardCharsets.UTF_8));
        return gson.fromJson(json, CompanyResponseDto.class);
    }


}
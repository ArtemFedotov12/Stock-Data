package com.start.stockdata.rest.controller.attribute.field_controller;

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

import static com.start.stockdata.utils.SecurityTestUtils.getAdmin;
import static com.start.stockdata.utils.SecurityTestUtils.initSecurityContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class FieldControllerFindAllByMainEntityIdTest extends AbstractIntegrationTest {

    @Sql(value = {"/sql/field_controller/find_all_by_id_main_entity/success/init-db.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/sql/field_controller/find_all_by_id_main_entity/success/clear-db.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void findAllByIdMainEntitySuccess200() throws Exception {
        final Set<FieldResponseDto> expectedResponseDtos = getSuccessDtos();

        final MvcResult mvcResult = this.mockMvc
                .perform(get("/companies/12/fields")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                        .with(initSecurityContext(getAdmin())))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        //get data from response
        Set<FieldResponseDto> actualResponseDtos = new HashSet<>(Arrays.asList(gson
                .fromJson(mvcResult
                        .getResponse()
                        .getContentAsString(), FieldResponseDto[].class)
        ));

        Assert.assertEquals(expectedResponseDtos, actualResponseDtos);

        Assert.assertEquals(1, actualResponseDtos
                .stream()
                .filter(item -> item.getId().equals(new Long(5)))
                .count());

        Assert.assertEquals(1, actualResponseDtos
                .stream()
                .filter(item -> item.getId().equals(new Long(6)))
                .count());

        Assert.assertEquals(1, actualResponseDtos
                .stream()
                .filter(item -> item.getId().equals(new Long(7)))
                .count());

    }


    @Test
    public void findAllByIdMainEntityUnauthorized401() throws Exception {
        this.mockMvc.perform(
                get("/companies/12/fields")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isUnauthorized());
    }

    @Sql(value = {"/sql/field_controller/find_all_by_id_main_entity/company_not_belong_to_current_user/init-db.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/sql/field_controller/find_all_by_id_main_entity/company_not_belong_to_current_user/clear-db.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void findAllByIdMainEntityNotBelongToCurrentUser400() throws Exception {
        final ErrorDto expectedResponseDto = getCompanyNotBelongToCurrentUserResponseDto();

        final MvcResult mvcResult = this.mockMvc
                .perform(get("/companies/25/fields")
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
    public void findAllByIdMainEntityCompanyNotExist400() throws Exception {
        final ErrorDto expectedResponseDto = getCompanyNotExistResponseDto();

        final MvcResult mvcResult = this.mockMvc
                .perform(get("/companies/499/fields")
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

    private ErrorDto getCompanyNotExistResponseDto() throws IOException {
        File file = new File("src/test/resources/json/field_controller/find_all_by_id_main_entity/company_not_exist/response-dto.json");
        String json = FileUtils.readFileToString(file, String.valueOf(StandardCharsets.UTF_8));
        return gson.fromJson(json, ErrorDto.class);
    }

    private ErrorDto getCompanyNotBelongToCurrentUserResponseDto() throws IOException {
        File file = new File("src/test/resources/json/field_controller/find_all_by_id_main_entity/company_not_belong_to_user/response-dto.json");
        String json = FileUtils.readFileToString(file, String.valueOf(StandardCharsets.UTF_8));
        return gson.fromJson(json, ErrorDto.class);
    }

    private Set<FieldResponseDto> getSuccessDtos() throws IOException {
        File file = new File("src/test/resources/json/field_controller/find_all_by_id_main_entity/success/response-dto.json");
        String json = FileUtils.readFileToString(file, String.valueOf(StandardCharsets.UTF_8));
        return new HashSet<>(Arrays.asList(gson.fromJson(json, FieldResponseDto[].class)));
    }

}

package com.start.stockdata.rest.controller;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class CompanyControllerTest extends AbstractIntegrationTest{

    @Autowired
    private MockMvc mockMvc;

/*    @Test
    void save_success() throws Exception {
        CompanyDto companyDto = new CompanyDto();
        companyDto.setName("companyName");
        companyDto.setUserId(2L);
        companyDto.setCompanyType("some");
        companyDto.setCompanyType(CompanyType.DAIRY.getValue());

        this.mockMvc
                .perform(post(COMPANIES_PATH)
                .content(gson.toJson(companyDto))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(gson.toJson(companyDto))));
    }*/
}
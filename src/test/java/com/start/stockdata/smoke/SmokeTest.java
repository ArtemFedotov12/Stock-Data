package com.start.stockdata.smoke;

import com.start.stockdata.StockDataApplication;
import com.start.stockdata.rest.controller.admin.AdminController;
import com.start.stockdata.rest.controller.user.CompanyController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StockDataApplication.class)
@ActiveProfiles("test")
public class SmokeTest {

    @Autowired
    private CompanyController companyController;
    @Autowired
    AdminController adminController;


    @Test
    public void contextLoads() throws Exception {
        assertNotNull(companyController);
        assertNotNull(adminController);
    }
}
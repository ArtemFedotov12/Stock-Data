package com.start.stockdata.rest.controller;

import com.google.gson.Gson;
import com.start.stockdata.StockDataApplication;
import com.start.stockdata.utils.DbRecreationTestListener;
import lombok.NoArgsConstructor;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StockDataApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
// for "MockMvc mockMvc"  to be injected
@AutoConfigureMockMvc
@NoArgsConstructor
//We use migration in this profile, in dev profile NOT
@ActiveProfiles("test")
// clean the whole data before test class (flyway.clean();)
@TestExecutionListeners(
        value = DbRecreationTestListener.class,
        mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS
)
/*
In this test(means with MockMvc mockMvc), the full Spring application context is started
 but without the server. We can narrow the tests to only the web layer by using @WebMvcTest
 @WebMvcTest doesn't work!!!!
 With @WebMvcTest Spring Boot instantiates only the web layer rather than the whole context.
 */
public abstract class AbstractIntegrationTest {

   @Autowired
   // not start the server at all but to test only the layer below
   protected MockMvc mockMvc;
   @Autowired
   protected Gson gson;

 /*  @BeforeClass
   @Sql(value = {"/sql/delete-create_stock_test_Db.sql"},
           executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
   public static void beforeClass() {

   }

   @AfterClass
   @Sql(value = {"/sql/delete-create_stock_test_Db.sql"},
           executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
   public static void afterClass() {

   }*/


}

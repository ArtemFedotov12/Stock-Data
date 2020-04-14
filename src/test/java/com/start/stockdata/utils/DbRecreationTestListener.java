package com.start.stockdata.utils;

import org.flywaydb.core.Flyway;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

public class DbRecreationTestListener extends AbstractTestExecutionListener {

    @Override
    public void beforeTestClass(TestContext testContext) {
        final Flyway flyway = testContext.getApplicationContext().getBean(Flyway.class);
        flyway.clean();
        flyway.migrate();
    }
}

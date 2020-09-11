package com.vmware;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(value = "/application.properties")
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class NumberGeneratorApplicationTests {
    @Autowired
    protected ApplicationContext context;

    @Test
    public void contextLoads() {
        Assert.assertNotNull(this.context);
    }
}


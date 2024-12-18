package com.example.portfoliocoreapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles({"local"})
@SpringBootTest
class PortfolioCoreApiApplicationTests {

    @Autowired
    private Environment env;

    @Test
    void contextLoads() {
    }

    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;

    @Test
    void importTest() {
        System.out.println(appName);
    }

    @Test
    public void testGetDataSourceUrl() {
        assertThat(env.getProperty("spring.datasource.url")).isEqualTo("jdbc:h2:mem:multi-prod");
    }

}

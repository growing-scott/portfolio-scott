package com.example.portfoliocoreapi;

import com.example.domainmysql.DomainMySqlConfiguration;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

import java.util.Arrays;

@Import(DomainMySqlConfiguration.class)
@SpringBootApplication
@RequiredArgsConstructor
public class PortfolioCoreApiApplication {

    private final Environment environment;

    public static void main(String[] args) {
        System.setProperty("spring.config.name", "application-core-api");
        SpringApplication.run(PortfolioCoreApiApplication.class, args);
    }

    @PostConstruct
    public void init() {
        System.out.println("Active profiles: " + Arrays.toString(environment.getActiveProfiles()));
        System.out.println("Property source names: " + environment.getProperty("spring.config.name"));
    }
}

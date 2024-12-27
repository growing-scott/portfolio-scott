package com.example.portfoliocouponapi;

import com.example.domainmysql.DomainMySqlConfiguration;
import com.example.domainredis.DomainRedisConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;

@EnableCaching
@Import({DomainMySqlConfiguration.class, DomainRedisConfiguration.class})
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class PortfolioCouponApiApplication {

    public static void main(String[] args) {
        System.setProperty("spring.config.name", "application-coupon-api");
        SpringApplication.run(PortfolioCouponApiApplication.class, args);
    }

}

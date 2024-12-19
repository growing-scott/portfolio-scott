package com.example.domainredis.domains.allowed.repository;

import com.example.domainredis.DomainRedisConfiguration;
import com.example.domainredis.config.ContainerRedisTestConfig;
import com.example.domainredis.config.LettuceConfig;
import com.example.domainredis.config.RedisConfig;
import com.example.domainredis.config.RedisDataTestConfig;
import com.example.domainredis.domains.allowed.entity.AllowedUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@ContextConfiguration(classes = {RedisConfig.class, LettuceConfig.class})
@Import({DomainRedisConfiguration.class, RedisDataTestConfig.class})
@DataRedisTest(properties = "spring.config.location=classpath:application-domain-redis.yml")
class AllowedUserRepositoryTest extends ContainerRedisTestConfig {

    @Autowired
    private AllowedUserRepository allowedUserRepository;

    @Test
    void allowedUser() {
        // given
        String regNo = "921108-1582817";
        String name = "동탁";

        // when
        allowedUserRepository.setAllowedUser(name, regNo);

        // then
        AllowedUser allowedUser = allowedUserRepository.getUser(regNo);
        assertEquals("허용된 유저가 맞습니다.", allowedUser.getName(), name);
    }

    @Test
    void getUser() {
    }
}

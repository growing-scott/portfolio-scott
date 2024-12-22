package com.example.domainredis.domains.allowed.service;

import com.example.domainredis.DomainRedisConfiguration;
import com.example.domainredis.aop.DistributedLockAspect;
import com.example.domainredis.config.ContainerRedisTestConfig;
import com.example.domainredis.config.LettuceConfig;
import com.example.domainredis.config.RedisConfig;
import com.example.domainredis.config.RedisDataTestConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@ContextConfiguration(classes = {RedisConfig.class, LettuceConfig.class})
@Import({DomainRedisConfiguration.class, RedisDataTestConfig.class})
@DataRedisTest(properties = "spring.config.location=classpath:application-domain-redis.yml")
class AllowedUserServiceTest extends ContainerRedisTestConfig {

    @Mock
    private DistributedLockAspect aspect;

    @Autowired
    private AllowedUserService allowedUserService;

    private AllowedUserService allowedUserServiceProxy;

    @Test
    void lock() {
        AspectJProxyFactory aspectJProxyFactory = new AspectJProxyFactory(allowedUserService);
        aspectJProxyFactory.addAspect(DistributedLockAspect.class); // fails
        allowedUserServiceProxy = aspectJProxyFactory.getProxy();
        allowedUserServiceProxy.lockAllowedUser(10L);
    }

}

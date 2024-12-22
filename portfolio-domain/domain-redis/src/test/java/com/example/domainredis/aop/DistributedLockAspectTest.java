package com.example.domainredis.aop;

import com.example.domainredis.DomainRedisConfiguration;
import com.example.domainredis.config.LettuceConfig;
import com.example.domainredis.config.RedisConfig;
import com.example.domainredis.config.RedisDataTestConfig;
import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {RedisConfig.class, LettuceConfig.class})
@Import({DomainRedisConfiguration.class, RedisDataTestConfig.class})
@DataRedisTest(properties = "spring.config.location=classpath:application-domain-redis.yml")
class DistributedLockAspectTest {

    @Mock
    private ProceedingJoinPoint joinPoint;

    @InjectMocks
    private DistributedLockAspect aspect;

    @Test
    void AOP테스트() throws Throwable {
        // Given


        Object lockAop = aspect.lock(joinPoint);

        // When


        // Then
        //assertEquals("타임아웃", lockAop);



    }
}

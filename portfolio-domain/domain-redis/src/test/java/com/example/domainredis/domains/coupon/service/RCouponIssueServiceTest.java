package com.example.domainredis.domains.coupon.service;

import com.example.domainredis.DomainRedisConfiguration;
import com.example.domainredis.config.ContainerRedisTestConfig;
import com.example.domainredis.config.LettuceConfig;
import com.example.domainredis.config.RedisConfig;
import com.example.domainredis.config.RedisDataTestConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.util.Collection;
import java.util.stream.IntStream;

import static com.example.domainredis.config.KeyConfig.getIssueRequestKey;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@ContextConfiguration(classes = {RedisConfig.class, LettuceConfig.class})
@Import({DomainRedisConfiguration.class, RedisDataTestConfig.class})
@DataRedisTest(properties = "spring.config.location=classpath:application-domain-redis.yml")
class RCouponIssueServiceTest {

    @Autowired
    private RCouponIssueService rCouponIssueService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @BeforeEach
    void clear() {
        Collection<String> redisKeys = redisTemplate.keys("*");
        redisTemplate.delete(redisKeys);
    }

    @Test
    @DisplayName("쿠폰 수량 검증 - 발급 가능 수량이 존재하면 true를 반환한다")
    void 쿠폰수량검증_발급가능() {
        // given
        int totalIssueQuantity = 20;
        long couponId = 1;

        // when
        boolean result = rCouponIssueService.availableTotalIssueQuantity(totalIssueQuantity, couponId);

        // then
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("쿠폰 수량 검증 - 발급 가능 수량이 모두 소진되면 false를 반환한다")
    void 쿠폰수량검증_모두소진() {
        // given
        int totalIssueQuantity = 10;
        long couponId = 1;

        IntStream.range(0, totalIssueQuantity).forEach(userId -> {
            redisTemplate.opsForSet().add(getIssueRequestKey(couponId), String.valueOf(userId));
        });

        // when
        boolean result = rCouponIssueService.availableTotalIssueQuantity(totalIssueQuantity, couponId);

        // then
        Assertions.assertFalse(result);
    }


}

package com.example.domainredis.domains.coupon.repository;

import com.example.domainredis.domains.coupon.entity.RCouponRequestUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RCouponIssueRepository {

    private final RedisTemplate redisTemplate;
    private final ObjectMapper objectMapper;

    /**
     * Sorted Set
     * @param couponId
     * @param userId
     * @param userName
     * @param score
     */
    public void issueCouponRequestWithSortedSet(Long couponId, Long userId, String userName, Long score) {
        String key = "issue:request.couponId=%s".formatted(couponId);

        RCouponRequestUser couponUser = RCouponRequestUser
                .builder()
                .id(userId)
                .name(userName)
                .build();

        redisTemplate.opsForZSet().addIfAbsent(key, couponUser, score);
    }

    public Long issueCouponRequestWithSet(String key, String value) {
        return redisTemplate.opsForSet().add(key, value);
    }

    public Long getSizeWithSet(String key) {
        return redisTemplate.opsForSet().size(key);
    }

    public Boolean isMemberWithSet(String key, String value) {
        return redisTemplate.opsForSet().isMember(key, value);
    }
}

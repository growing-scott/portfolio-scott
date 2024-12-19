package com.example.domainredis.domains.allowed.repository;

import com.example.domainredis.domains.allowed.entity.AllowedUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class AllowedUserRepository {

    private final RedisTemplate<String, String> redisTemplate;
    private final ObjectMapper objectMapper;

    public void setAllowedUser(String name, String regNo) {
        AllowedUser allowedUser = AllowedUser
                .builder()
                .name(name)
                .regNo(regNo)
                .build();
        try {
            redisTemplate.opsForValue().set(regNo, objectMapper.writeValueAsString(allowedUser));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public AllowedUser getUser(String regNo) {
        try {
            return objectMapper.readValue(redisTemplate.opsForValue().get(regNo), AllowedUser.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("찾을수 없다냥");
        }
    }
}

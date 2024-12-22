package com.example.domainredis.domains.allowed.service;

import com.example.domainredis.annotation.DistributedLock;
import com.example.portfoliocore.annotation.DomainService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@DomainService
public class AllowedUserService {

    @DistributedLock(key = "test")
    public void lockAllowedUser(Long userId) {
        log.info("lockAllowedUser {}", userId);
    }
}

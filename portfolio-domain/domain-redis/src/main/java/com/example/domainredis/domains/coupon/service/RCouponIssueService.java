package com.example.domainredis.domains.coupon.service;

import com.example.domainredis.domains.coupon.repository.RCouponIssueRepository;
import com.example.portfoliocore.annotation.DomainService;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class RCouponIssueService {

    private RCouponIssueRepository rCouponIssueRepository;


    public void couponIssueRequest(Long couponId, Long userId, String userName) {
        rCouponIssueRepository.issueCouponRequest(couponId, userId, userName, System.currentTimeMillis());
    }

}

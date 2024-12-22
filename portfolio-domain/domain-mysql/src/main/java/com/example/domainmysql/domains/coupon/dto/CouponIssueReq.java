package com.example.domainmysql.domains.coupon.dto;

import com.example.domainmysql.domains.coupon.entity.CouponIssue;

public record CouponIssueReq(Long couponId, Long userId) {
    public CouponIssue toEntity() {
        return CouponIssue.builder()
                .couponId(couponId)
                .userId(userId)
                .build();
    }
}

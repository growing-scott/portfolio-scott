package com.example.domainmysql.domains.coupon.dto;

import com.example.domainmysql.domains.coupon.entity.Coupon;
import com.example.domainmysql.domains.coupon.entity.CouponIssue;
import com.example.domainmysql.domains.coupon.enums.CouponType;

import java.time.LocalDateTime;

public record CouponIssueReq(Long couponId, Long userId) {
    public CouponIssue toEntity() {
        return CouponIssue.builder()
                .couponId(couponId)
                .userId(userId)
                .dateIssued(LocalDateTime.now())
                .build();
    }
}

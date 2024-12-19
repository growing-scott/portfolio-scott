package com.example.domainmysql.domains.coupon.dto;

import com.example.domainmysql.domains.coupon.entity.Coupon;
import com.example.domainmysql.domains.coupon.enums.CouponType;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

public record CouponCreateReq(String title, CouponType couponType, Integer totalQuantity, LocalDateTime dateIssueStart, LocalDateTime dateIssuedEnd) {
    public Coupon toEntity() {
        return Coupon.builder()
                .title(title)
                .couponType(couponType)
                .totalQuantity(totalQuantity)
                .issuedQuantity(0)
                .discountAmount(0)
                .minAvailableAmount(0)
                .dateIssueStart(dateIssueStart)
                .dateIssueEnd(dateIssuedEnd)
                .build();
    }
}

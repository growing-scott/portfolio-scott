package com.example.portfoliocouponapi.apis.coupon.dto;

import com.example.domainmysql.domains.coupon.entity.Coupon;
import com.example.domainmysql.domains.coupon.enums.CouponType;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDateTime;

public record CouponCache(

    Long id,

    CouponType couponType,

    Integer totalQuantity,

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    LocalDateTime dateIssueStart,

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    LocalDateTime dateIssueEnd
) {

    public CouponCache(Coupon coupon) {
        this(coupon.getId(), coupon.getCouponType(), coupon.getTotalQuantity(), coupon.getDateIssueStart(), coupon.getDateIssueEnd());
    }

    private boolean availableIssueDate() {
        LocalDateTime now = LocalDateTime.now();
        return dateIssueStart.isBefore(now) && dateIssueEnd.isAfter((now));
    }

    public void checkIssuableCoupon() {
        if (!availableIssueDate()) {
            throw new RuntimeException("쿠폰 발급 가능 날짜가 아닙니다.");
        }
    }
}

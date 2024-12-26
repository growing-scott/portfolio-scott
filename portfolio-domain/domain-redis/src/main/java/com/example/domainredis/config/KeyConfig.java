package com.example.domainredis.config;

public class KeyConfig {

    public static String getIssueRequestKey(long couponId) {
        return "coupon.issue.request.id=%s".formatted(couponId);
    }

}

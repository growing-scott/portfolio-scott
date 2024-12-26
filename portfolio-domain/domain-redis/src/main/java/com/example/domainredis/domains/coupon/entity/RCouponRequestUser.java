package com.example.domainredis.domains.coupon.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RCouponRequestUser {

    private Long id;
    private String name;

    @Builder
    private RCouponRequestUser(Long id, String name) {
        this.name = name;
        this.id = id;
    }

}

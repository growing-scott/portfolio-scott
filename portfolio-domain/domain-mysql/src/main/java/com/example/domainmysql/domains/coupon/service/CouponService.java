package com.example.domainmysql.domains.coupon.service;

import com.example.domainmysql.domains.coupon.dto.CouponCreateReq;
import com.example.domainmysql.domains.coupon.repository.CouponRepository;
import com.example.portfoliocore.annotation.DomainService;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository couponRepository;

    public Long createCoupon(CouponCreateReq couponCreateReq) {
        return couponRepository.save(couponCreateReq.toEntity()).getId();
    }
}

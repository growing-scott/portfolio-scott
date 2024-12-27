package com.example.domainmysql.domains.coupon.service;

import com.example.domainmysql.domains.coupon.dto.CouponCreateReq;
import com.example.domainmysql.domains.coupon.entity.Coupon;
import com.example.domainmysql.domains.coupon.repository.CouponRepository;
import com.example.portfoliocore.annotation.DomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@DomainService
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository couponRepository;

    public Long createCoupon(CouponCreateReq couponCreateReq) {
        return couponRepository.save(couponCreateReq.toEntity()).getId();
    }

    @Transactional(readOnly = true)
    public Coupon findCoupon(long couponId) {
        log.info("findCoupon {}", couponId);
        return couponRepository.findById(couponId).orElseThrow(() -> new RuntimeException("쿠폰없음"));
    }
}

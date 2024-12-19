package com.example.portfoliocouponapi.apis.coupon.usecase;

import com.example.domainmysql.domains.coupon.dto.CouponCreateReq;
import com.example.domainmysql.domains.coupon.dto.CouponIssueReq;
import com.example.domainmysql.domains.coupon.service.CouponIssueService;
import com.example.domainmysql.domains.coupon.service.CouponService;
import com.example.domainmysql.domains.user.dto.UserSignupReq;
import com.example.domainmysql.domains.user.service.UserService;
import com.example.portfoliocore.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@UseCase
@RequiredArgsConstructor
public class CouponUseCase {

    private final CouponService couponService;

    private final CouponIssueService couponIssueService;

    public Long createCoupon(final CouponCreateReq couponCreateReq) {
        return couponService.createCoupon(couponCreateReq);
    }

    @Transactional
    public Long issueCoupon(CouponIssueReq couponIssueReq) {
        return couponIssueService.issueCoupon(couponIssueReq);
    }

}

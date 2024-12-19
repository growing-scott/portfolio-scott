package com.example.portfoliocouponapi.apis.coupon.controller;

import com.example.domainmysql.domains.coupon.dto.CouponCreateReq;
import com.example.domainmysql.domains.coupon.dto.CouponIssueReq;
import com.example.portfoliocouponapi.apis.coupon.dto.CouponCreatedRes;
import com.example.portfoliocouponapi.apis.coupon.dto.CouponIssueRes;
import com.example.portfoliocouponapi.apis.coupon.usecase.CouponUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/coupon/v1/coupons")
public class CouponController {

    private final CouponUseCase couponUseCase;

    @PostMapping
    public ResponseEntity<CouponCreatedRes> createCoupon(@RequestBody CouponCreateReq couponCreateReq) {
        return new ResponseEntity<>(new CouponCreatedRes(couponUseCase.createCoupon(couponCreateReq)), HttpStatus.CREATED);
    }

    @PostMapping(name = "/issues")
    public ResponseEntity<CouponIssueRes> issueCoupon(@RequestBody CouponIssueReq couponIssueReq) {
        return new ResponseEntity<>(new CouponIssueRes(couponUseCase.issueCoupon(couponIssueReq)), HttpStatus.CREATED);
    }

    @PostMapping(name = "/issues/synchronized")
    public ResponseEntity<CouponIssueRes> issueCouponSynchronized(@RequestBody CouponIssueReq couponIssueReq) {
        Long issueId;
        synchronized (this) {
            issueId = couponUseCase.issueCoupon(couponIssueReq);
        }
        return new ResponseEntity<>(new CouponIssueRes(issueId), HttpStatus.CREATED);
    }

    @PostMapping(name = "/issues/redis-lock")
    public ResponseEntity<CouponIssueRes> issueCouponWithRedisLock(@RequestBody CouponIssueReq couponIssueReq) {
        Long issueId = 0L;

        return new ResponseEntity<>(new CouponIssueRes(issueId), HttpStatus.CREATED);
    }
}

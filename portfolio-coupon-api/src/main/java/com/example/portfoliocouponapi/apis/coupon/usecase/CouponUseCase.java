package com.example.portfoliocouponapi.apis.coupon.usecase;

import com.example.domainmysql.domains.coupon.dto.CouponCreateReq;
import com.example.domainmysql.domains.coupon.dto.CouponIssueReq;
import com.example.domainmysql.domains.coupon.entity.Coupon;
import com.example.domainmysql.domains.coupon.service.CouponIssueService;
import com.example.domainmysql.domains.coupon.service.CouponService;
import com.example.domainmysql.domains.user.dto.UserSignupReq;
import com.example.domainmysql.domains.user.service.UserService;
import com.example.domainredis.annotation.DistributedLock;
import com.example.domainredis.domains.coupon.service.RCouponIssueService;
import com.example.portfoliocore.annotation.UseCase;
import com.example.portfoliocouponapi.apis.coupon.dto.CouponCache;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.Map;

@Slf4j
@UseCase
@RequiredArgsConstructor
public class CouponUseCase {

    private final CouponService couponService;

    private final CouponIssueService couponIssueService;

    private final RCouponIssueService rCouponIssueService;

    public Long createCoupon(final CouponCreateReq couponCreateReq) {
        return couponService.createCoupon(couponCreateReq);
    }

    @Cacheable(value = "coupon", cacheManager = "couponCacheManager")
    public CouponCache getCouponCache(long couponId) {
        Coupon coupon = couponService.findCoupon(couponId);
        log.info("coupon {}", coupon);
        return new CouponCache(coupon);
    }

    @Transactional
    public Long issueCoupon(CouponIssueReq couponIssueReq) {
        return couponIssueService.issueCoupon(couponIssueReq);
    }

    @Transactional
    @DistributedLock(key="'coupon-' + #couponId", waitTime = 5, leaseTime = 7)
    public Long issueCouponWithRedisLock(CouponIssueReq couponIssueReq, Long couponId) {
        Object transactionId = TransactionSynchronizationManager.getCurrentTransactionName();

        log.info("issueCouponWithRedisLock couponId: {}, userId: {}, transactionId: {}", couponIssueReq.couponId(), couponIssueReq.userId(), transactionId.toString());

        //Long couponIssuedId = couponIssueService.issueCoupon(couponIssueReq);
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("#### 쿠폰 발급 완료: {}, transactionId: {}", 10L, TransactionSynchronizationManager.getCurrentTransactionName());
        return 10L;
    }

    public Long issueCouponRequestWithAsync(CouponIssueReq couponIssueReq) {
        rCouponIssueService.couponIssueRequest(couponIssueReq.couponId(), couponIssueReq.userId(), "테스트");

        return 10L;
    }

    public Long issueCouponRequestWithSet(CouponIssueReq couponIssueReq) {
        rCouponIssueService.issueCouponRequestWithSet(couponIssueReq.couponId(), couponIssueReq.userId());
        return 10L;
    }

    public Long issueCouponRequestWithSetAndCache(CouponIssueReq couponIssueReq) {
        CouponCache coupon = getCouponCache(couponIssueReq.couponId());
        coupon.checkIssuableCoupon();

        rCouponIssueService.issueCouponRequestWithSet(couponIssueReq.couponId(), couponIssueReq.userId());
        return 10L;
    }


}

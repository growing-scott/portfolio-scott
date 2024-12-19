package com.example.domainmysql.domains.coupon.service;

import com.example.domainmysql.domains.coupon.dto.CouponIssueReq;
import com.example.domainmysql.domains.coupon.entity.Coupon;
import com.example.domainmysql.domains.coupon.entity.CouponIssue;
import com.example.domainmysql.domains.coupon.repository.CouponIssueRepository;
import com.example.domainmysql.domains.coupon.repository.CouponRepository;
import com.example.portfoliocore.annotation.DomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@DomainService
@RequiredArgsConstructor
public class CouponIssueService {

    private final CouponRepository couponRepository;

    private final CouponIssueRepository couponIssueRepository;

    @Transactional
    public Long issueCoupon(CouponIssueReq couponIssueReq) {
        Coupon coupon = findCoupon(couponIssueReq.couponId());
        coupon.issue();
        CouponIssue couponIssue = saveCouponIssue(couponIssueReq.couponId(), couponIssueReq.userId());
        return couponIssue.getId();
    }

    @Transactional
    public CouponIssue saveCouponIssue(long couponId, long userId) {
        checkAlreadyIssuance(couponId, userId);
        CouponIssue couponIssue = CouponIssue.builder()
                .couponId(couponId)
                .userId(userId)
                .build();
        return couponIssueRepository.save(couponIssue);
    }

    @Transactional(readOnly = true)
    public Coupon findCoupon(long couponId) {
        return couponRepository.findById(couponId).orElseThrow(() -> {
            throw new RuntimeException("쿠폰 정책이 존재하지 않습니다. %s".formatted(couponId));
        });
    }

    private void checkAlreadyIssuance(long couponId, long userId) {
        Optional<CouponIssue> issue = couponIssueRepository.findByCouponIdAndUserId(couponId, userId);
        if (issue.isPresent()) {
            throw new RuntimeException("이미 발급된 쿠폰입니다. user_id: %d, coupon_id: %d".formatted(userId, couponId));
        }
    }

}

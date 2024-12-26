package com.example.domainredis.domains.coupon.service;

import com.example.domainredis.domains.coupon.repository.RCouponIssueRepository;
import com.example.portfoliocore.annotation.DomainService;
import lombok.RequiredArgsConstructor;

import static com.example.domainredis.config.KeyConfig.getIssueRequestKey;

@DomainService
@RequiredArgsConstructor
public class RCouponIssueService {

    private final RCouponIssueRepository rCouponIssueRepository;

    public void couponIssueRequest(Long couponId, Long userId, String userName) {
        rCouponIssueRepository.issueCouponRequestWithSortedSet(couponId, userId, userName, System.currentTimeMillis());
    }

    public void issueCouponRequestWithSet(Long couponId, Long userId) {


    }

    public boolean availableTotalIssueQuantity(Integer totalQuantity, long couponId) {
        if (totalQuantity == null) {
            return true;
        }
        String key = getIssueRequestKey(couponId);
        return totalQuantity > rCouponIssueRepository.getSizeWithSet(key);
    }

    public boolean availableUserIssueQuantity(Long couponId, Long userId) {
        String key = getIssueRequestKey(couponId);
        return !rCouponIssueRepository.isMemberWithSet(key,String.valueOf(userId));
    }
}

package com.example.domainmysql.domains.coupon.repository;

import com.example.domainmysql.domains.coupon.entity.Coupon;
import com.example.domainmysql.domains.coupon.entity.CouponIssue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

public interface CouponIssueRepository extends JpaRepository<CouponIssue, Long> {
    Optional<CouponIssue> findByCouponIdAndUserId(Long couponId, Long userId);

}

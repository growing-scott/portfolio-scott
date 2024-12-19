package com.example.domainmysql.domains.coupon.repository;

import com.example.domainmysql.domains.coupon.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
}

package com.example.domainmysql.domains.coupon.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "coupon_issues")
public class CouponIssue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long couponId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    @CreatedDate
    private LocalDateTime dateIssued;

    private LocalDateTime dateUsed;

    @Builder
    public CouponIssue(Long couponId, Long userId) {
        this.couponId = couponId;
        this.userId = userId;
        this.dateIssued = LocalDateTime.now();
    }
}

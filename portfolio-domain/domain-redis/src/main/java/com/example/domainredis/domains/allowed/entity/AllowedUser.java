package com.example.domainredis.domains.allowed.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AllowedUser {

    private String name;
    private String regNo;

    @Builder
    private AllowedUser(String name, String regNo) {
        this.name = name;
        this.regNo = regNo;
    }

}

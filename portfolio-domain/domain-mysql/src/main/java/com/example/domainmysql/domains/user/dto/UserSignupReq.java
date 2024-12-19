package com.example.domainmysql.domains.user.dto;

import com.example.domainmysql.domains.user.entity.User;

public record UserSignupReq(
        String loginId,
        String password,
        String name,
        String regNo
) {
    public User toEntity(String encryptedPassword, String regNoEnc, String regNoHash, String salt) {
        return User.builder()
                .loginId(loginId)
                .name(name)
                .passwordHash(encryptedPassword)
                .regNoEnc(regNoEnc)
                .regNoHash(regNoHash)
                .salt(salt)
                .build();
    }

}

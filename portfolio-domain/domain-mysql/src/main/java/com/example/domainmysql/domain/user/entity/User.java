package com.example.domainmysql.domain.user.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Getter
@Table(name="study_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login_id", nullable = false, length = 50)
    private String loginId;

    @Column(name = "password_hash", nullable = false, length = 60)
    private String passwordHash;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "reg_no_enc", nullable = false, length = 14)
    private String regNoEnc;

    @Column(name = "reg_no_hash", nullable = false, length = 100)
    private String regNoHash;

    @Column(name = "salt", nullable = false, length = 36)
    private String salt;

    @Builder
    private User(String loginId, String passwordHash, String name, String regNoEnc, String regNoHash, String salt) {
        this.loginId = loginId;
        this.passwordHash = passwordHash;
        this.name = name;
        this.regNoEnc = regNoEnc;
        this.regNoHash = regNoHash;
        this.salt = salt;
    }
}

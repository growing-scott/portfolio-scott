package com.example.domainmysql.domains.user.service;

import com.example.domainmysql.domains.user.dto.UserSignupReq;
import com.example.domainmysql.domains.user.entity.User;
import com.example.domainmysql.domains.user.repository.UserRepository;
import com.example.portfoliocore.annotation.DomainService;
import com.example.portfoliocore.util.EncryptionHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

@RequiredArgsConstructor
@DomainService
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public Long signup(UserSignupReq userSignupReq) {
        String regNoHash = hashRegNo(userSignupReq.regNo());
        /*if (getUserByLoginId(signupDTO.getUserId()).isPresent() || existRegNo(regNoHash)) {
            throw new AlreadyUserException();
        }*/

        String salt = UUID.randomUUID().toString();
        User user = userSignupReq.toEntity(
            encryptPassword(userSignupReq.password()),
            encryptRegNo(userSignupReq.regNo(), userSignupReq.name(), salt),
            hashRegNo(userSignupReq.regNo()),
            salt
        );
        return userRepository.save(user).getId();
    }

    public String encryptPassword(String plainPassword) {
        return passwordEncoder.encode(plainPassword);
    }

    private String hashRegNo(String regNo) {
        try {
            return EncryptionHelper.hash(regNo);
        } catch (Exception e) {
            throw new RuntimeException("Failed Encrypt Hash.");
        }
    }

    private String encryptRegNo(String regNo, String name, String salt) {
        try {
            return EncryptionHelper.encrypt(regNo, name.toCharArray(), salt);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    private String decryptRegNo(String regNo, String name, String salt) {
        try {
            return EncryptionHelper.decrypt(regNo, name.toCharArray(), salt);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

}

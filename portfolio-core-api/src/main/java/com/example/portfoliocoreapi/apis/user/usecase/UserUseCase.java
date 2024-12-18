package com.example.portfoliocoreapi.apis.user.usecase;

import com.example.domainmysql.domain.user.dto.request.UserSignupReq;
import com.example.domainmysql.domain.user.service.UserService;
import com.example.portfoliocore.annotation.UseCase;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@UseCase
@RequiredArgsConstructor
public class UserUseCase {

    private final UserService userService;

    private final Map<String, String> allowSignupUser = Map.of(
    "동탁", "921108-1582817",
    "관우", "681108-1682818",
    "손권", "890601-2355119",
    "유비", "790411-1656210",
    "조조", "810326-2715702"
    );

    public Long signup(final UserSignupReq userSignupReq) {
        if (!allowSignupUser.containsKey(userSignupReq.name()) || !allowSignupUser.get(userSignupReq.name()).equals(userSignupReq.regNo())) {
            throw new RuntimeException("가입할 수 없는 사용자입니다. 고객센터에 문의해주세요.");
        }

        return userService.signup(userSignupReq);
    }

}

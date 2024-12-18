package com.example.portfoliocoreapi.apis.user.controller;

import com.example.domainmysql.domain.user.dto.request.UserSignupReq;
import com.example.portfoliocoreapi.apis.user.dto.UserSignupRes;
import com.example.portfoliocoreapi.apis.user.usecase.UserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/core/v1/users")
public class UserController {

    private final UserUseCase userUseCase;

    @PostMapping
    public ResponseEntity<UserSignupRes> signup(@RequestBody UserSignupReq userSignupReq) {
        return new ResponseEntity<>(new UserSignupRes(userUseCase.signup(userSignupReq)), HttpStatus.CREATED);
    }

    @GetMapping
    public void getUsers() {

    }

}

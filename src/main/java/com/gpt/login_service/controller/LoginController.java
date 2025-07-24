package com.gpt.login_service.controller;

import com.gpt.login_service.models.LoginResponse;
import com.gpt.login_service.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {
    @Autowired
    LoginService loginService;

    @PostMapping
    public LoginResponse loginUser(@RequestParam String email) {
        return loginService.loginUser(email);
    }
}

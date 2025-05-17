package com.gpt.login_service.controller;

import lombok.AllArgsConstructor;
import com.gpt.login_service.models.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.gpt.login_service.service.RegistrationService;

@RestController
@RequestMapping(path = "/api/v1/register")
@AllArgsConstructor
public class RegistrationController {
    @Autowired
    RegistrationService registrationService;
    @PostMapping
    public String register(@RequestBody RegisterRequest registerRequest){
        return registrationService.register(registerRequest);
    }

    @GetMapping("/confirmToken")
    public String confirmToken(@RequestParam("token") String token){
        return registrationService.confirmToken(token);
    }
}

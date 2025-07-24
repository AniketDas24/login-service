package com.gpt.login_service.service;

import com.gpt.login_service.helper.EmailValidator;
import com.gpt.login_service.models.AppUser;
import com.gpt.login_service.models.UserRole;
import com.gpt.login_service.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import com.gpt.login_service.models.RegisterRequest;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegistrationService {
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    EmailValidator emailValidator;
    @Autowired
    AppUserService appUserService;
    @Autowired
    EmailService emailService;
    @SneakyThrows
    public String register(RegisterRequest registerRequest) {
    boolean isValidEmail = emailValidator.checkEmail(registerRequest.getEmail());
        if(!isValidEmail){
            throw  new BadRequestException("Email already present!");
        }
        AppUser appUser = new AppUser(registerRequest.getFirstName(),
                registerRequest.getLastName(),
                registerRequest.getPassword(),
                registerRequest.getEmail(),
                UserRole.USER);
        String token = appUserService.save(appUser);
        emailService.sendVerificationTokenEmail(registerRequest.getEmail(),token);
        return "Token Sent "+ token;
    }

    public String confirmToken(String token) {
        appUserService.enableUserForLogIn(jwtUtils.getUserNameFromJwtToken(token));
        return "Email verified!";
    }
}

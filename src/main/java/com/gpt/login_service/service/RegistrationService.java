package com.gpt.login_service.service;

import com.gpt.login_service.helper.EmailValidator;
import com.gpt.login_service.models.AppUser;
import com.gpt.login_service.models.ConfirmationToken;
import lombok.RequiredArgsConstructor;
import com.gpt.login_service.models.RegisterRequest;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegistrationService {
    @Autowired
    EmailValidator emailValidator;
    @Autowired
    AppUserService appUserService;
    @Autowired
    ConfirmationTokenService confirmationTokenService;
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
                registerRequest.getRoleRequested());
        String token = appUserService.save(appUser);
        emailService.sendVerificationTokenEmail(registerRequest.getEmail(),token);
        return "Token Sent "+ token;
    }

    public String confirmToken(String token) {
        log.info("Confirmation of token: {} is initiated", token);
        ConfirmationToken confirmationToken = confirmationTokenService.getToken(token)
                .orElseThrow(() ->new IllegalStateException("Token not Found!"));
        if(Objects.nonNull(confirmationToken.getVerifiedAt())){
            throw new IllegalStateException("Email Already Verified");
        }
        if(confirmationToken.getExpiredAt().isBefore(LocalDateTime.now())){
            throw new IllegalStateException("Verification Link Expired");
        }
        confirmationTokenService.updateVerifiedAt(token);
        appUserService.enableUserForLogIn(confirmationToken.getAppUser());
        return "Email verified!";
    }
}

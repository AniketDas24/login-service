package com.gpt.login_service.service;

import com.gpt.login_service.helper.EmailValidator;
import com.gpt.login_service.models.AppUser;
import lombok.RequiredArgsConstructor;
import com.gpt.login_service.models.RegisterRequest;
import lombok.SneakyThrows;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationService {
    @Autowired
    EmailValidator emailValidator;
    @Autowired
    AppUserService appUserService;
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
                registerRequest.getRoleRequested(),
                true,
                true);
        appUserService.save(appUser);
        return "User Registered";
    }
}

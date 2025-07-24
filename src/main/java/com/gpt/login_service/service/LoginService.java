package com.gpt.login_service.service;

import com.gpt.login_service.models.*;
import com.gpt.login_service.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {
    private final AppUserService appUserService;
    private final JwtUtils jwtUtils;

    public LoginResponse loginUser(String email){
        AppUser user = appUserService.getAppUserByEmail(email);

        if (!user.getIsEnabled()) {
            return (new LoginResponse("Account is not verified yet."));
        }

        String jwt = jwtUtils.generateTokenFromUserDetails(user);

        return  new LoginResponse(
                        jwt,
                        null,
                        "Bearer",
                        user.getEmail(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getUserRole().name()
                );
    }
}

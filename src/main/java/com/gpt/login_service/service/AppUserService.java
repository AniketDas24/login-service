package com.gpt.login_service.service;


import com.gpt.login_service.models.AppUser;
import com.gpt.login_service.utils.JwtUtils;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.gpt.login_service.repository.AppUserRepository;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {
    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtUtils jwtUtils;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found with username: "+ email));
    }

    @SneakyThrows
    public String save(AppUser appUser) {
        Boolean isUserPresent = appUserRepository.findByEmail(appUser.getEmail()).isPresent();
        if(isUserPresent){
            log.error("User already present with same email {}", appUser.getEmail());
            throw new BadRequestException("Email already present");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encodedPassword);
        appUserRepository.save(appUser);
        String token = jwtUtils.generateTokenFromUserDetails(appUser);
        return token;
    }

    @Transactional
    public int enableUserForLogIn(String email){
        log.info("Enabling user for login for User with Email : {}", email);
        return appUserRepository.enableUser(email);
    }

    public AppUser getAppUserByEmail(String email) {
        return appUserRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }
}

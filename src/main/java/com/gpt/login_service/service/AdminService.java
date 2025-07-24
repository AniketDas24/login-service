package com.gpt.login_service.service;

import com.gpt.login_service.models.AppUser;
import com.gpt.login_service.models.CreateAdminRequest;
import com.gpt.login_service.models.CreateAdminResponse;
import com.gpt.login_service.models.UserRole;
import com.gpt.login_service.repository.AppUserRepository;
import com.gpt.login_service.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminService {
    @Autowired
    AppUserRepository appUserRepository;
    @Autowired
    AppUserService appUserService;
    @Autowired
    JwtUtils jwtUtils;
    public Boolean checkIfAdminAlreadyExists(){
        log.info("Checking if admin already created");
        Optional<AppUser> existingAdmin = appUserRepository.findByUserRole(UserRole.ADMIN);
        return existingAdmin.isPresent();
    }
    @SneakyThrows
    public CreateAdminResponse createAdmin(CreateAdminRequest createAdminRequest){
        log.info("Creating Admin for adminRequest: {}", createAdminRequest.toString());
        Boolean isAdminPresent = checkIfAdminAlreadyExists();
        if(isAdminPresent){
            throw  new BadRequestException("Admin already present!");
        }
        AppUser adminUser = new AppUser(createAdminRequest.getFirstName(),
                createAdminRequest.getLastName(),
                createAdminRequest.getPassword(),
                createAdminRequest.getEmail(),
                UserRole.ADMIN);
        appUserService.save(adminUser);
        String adminToken = jwtUtils.generateTokenFromUserDetails(adminUser);
        log.info("Admin token generated : "+ adminToken);
        return CreateAdminResponse
                .builder()
                .adminFirstName(createAdminRequest.getFirstName())
                .adminLastName(createAdminRequest.getLastName())
                .adminEmail(createAdminRequest.getEmail())
                .adminPassword(createAdminRequest.getPassword())
                .adminToken(adminToken)
                .build();
    }
}

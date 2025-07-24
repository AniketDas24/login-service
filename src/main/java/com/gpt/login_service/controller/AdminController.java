package com.gpt.login_service.controller;

import com.gpt.login_service.models.CreateAdminRequest;
import com.gpt.login_service.models.CreateAdminResponse;
import com.gpt.login_service.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/admin")
@AllArgsConstructor
public class AdminController {
    @Autowired AdminService adminService;
    @PostMapping(path = "/createAdmin")
    public CreateAdminResponse createAdmin(@RequestBody CreateAdminRequest createAdminRequest){
        return adminService.createAdmin(createAdminRequest);
    }
}

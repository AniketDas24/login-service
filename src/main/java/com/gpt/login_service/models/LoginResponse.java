package com.gpt.login_service.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private String accessToken;
    private String message;
    private String tokenType = "Bearer";
    private String email;
    private String firstName;
    private String lastName;
    private String role;
    public LoginResponse(String message) {
        this.accessToken = null;
        this.message = message;
        this.tokenType = null;
        this.email = null;
        this.firstName = null;
        this.lastName = null;
        this.role = null;
    }
}
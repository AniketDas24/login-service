package com.gpt.login_service.models;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
@NoArgsConstructor
public class CreateAdminResponse {
    private String adminFirstName;
    private String adminLastName;
    private String adminEmail;
    private String adminPassword;
    private String adminToken;
}

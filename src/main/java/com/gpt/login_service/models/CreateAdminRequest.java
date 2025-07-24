package com.gpt.login_service.models;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class CreateAdminRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}

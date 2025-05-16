package com.gpt.login_service.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;


@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity(name = "users")
public class AppUser implements UserDetails {

    @Id
    @SequenceGenerator(name = "userSequenceGenerator",
                        sequenceName = "userSequenceGenerator",
                        allocationSize = 1)
    @GeneratedValue( strategy = GenerationType.SEQUENCE,
            generator = "userSequenceGenerator")
    private Long id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    private Boolean isLocked;
    private Boolean isExpired;

    public AppUser(String firstName,
                   String lastName,
                   String password,
                   String email,
                   UserRole userRole,
                   Boolean isLocked,
                   Boolean isExpired) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.userRole = userRole;
        this.isLocked = isLocked;
        this.isExpired = isExpired;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userRole.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return firstName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !isExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !isExpired;
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}

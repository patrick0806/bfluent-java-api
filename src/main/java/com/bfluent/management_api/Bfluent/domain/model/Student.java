package com.bfluent.management_api.Bfluent.domain.model;

import com.bfluent.management_api.Bfluent.domain.model.enums.DocumentType;
import com.bfluent.management_api.Bfluent.domain.model.enums.Role;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Data
@Builder
public class Student implements UserDetails {
    private String id;
    private String code;
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private Address address;
    private DocumentType documentType;
    private String documentNumber;
    private Boolean isForeigner = false;
    private Boolean isActive = true;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(Role.STUDENT.toString()));
    }

    @Override
    public String getUsername() {
        return email;
    }
}

package ru.careportal.core.dto;

import org.springframework.security.core.GrantedAuthority;


public enum Role implements GrantedAuthority {
    ROLE_ADMIN,
    ROLE_PATIENT,
    ROLE_DOCTOR;

    @Override
    public String getAuthority() {
        return name();
    }
}

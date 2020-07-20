package ru.careportal.core.dto;

import org.springframework.security.core.GrantedAuthority;


public enum Role implements GrantedAuthority {
            ADMIN, CLIENT, DOCTOR;

    @Override
    public String getAuthority() {
        return name();
    }
}

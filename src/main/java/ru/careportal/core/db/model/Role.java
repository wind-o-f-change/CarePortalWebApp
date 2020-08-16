package ru.careportal.core.db.model;

import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;


public enum Role implements GrantedAuthority {
    ROLE_ADMIN("Администратор"),
    ROLE_PATIENT("Пациент"),
    ROLE_DOCTOR("Доктор");

    private final String rusName;

    Role(String rusName) {
        this.rusName = rusName;
    }

    public String getRusName() {
        return rusName;
    }

    public static List<String> getRoleList() {
        List<String> roleList = new ArrayList<>();
        for (Role role : Role.values()) {
            roleList.add(role.getRusName());
        }
        return roleList;
    }

    public static Role getRoleByRusName(String rusName) {
        for (Role role : values()) {
            if (role.getRusName().equals(rusName)) {
                return role;
            }
        }

        throw new IllegalArgumentException("No enum found with url: [" + rusName + "]");
    }

    public static List<Role> getEnumListByRusNameList(List<String> rusNameList) {
        List<Role> roleList = new ArrayList<>();
        for(String rusName : rusNameList) {
            roleList.add(getRoleByRusName(rusName));
        }
        return roleList;
    }

    @Override
    public String getAuthority() {
        return name();
    }
}

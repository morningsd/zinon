package edu.demian.zinon.entity;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;

public enum Role {
    ROLE_USER,
    ROLE_ADMIN;

    public Collection<SimpleGrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(name()));
    }
}

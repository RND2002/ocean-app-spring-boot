package com.udemyapi.udemyclone.entity;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Set;

public record UserMapper(
         String firstname,
         String lastname,

        String email
        // Collection<? extends GrantedAuthority> roles
        //.Set<String> roles
) {
}

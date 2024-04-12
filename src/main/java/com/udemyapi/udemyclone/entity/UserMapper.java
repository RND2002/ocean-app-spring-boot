package com.udemyapi.udemyclone.entity;

import java.util.Set;

public record UserMapper (
         String firstname,
         String lastname,

        String email,
        // Collection<? extends GrantedAuthority> roles
        Set<Role> roles,

         String errorMessage) {
    public static UserMapper withErrorMessage(String errorMessage) {
        return new UserMapper(null, null, null, null, errorMessage);
    }
}

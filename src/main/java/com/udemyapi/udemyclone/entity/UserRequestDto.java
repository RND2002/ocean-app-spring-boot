package com.udemyapi.udemyclone.entity;

import java.util.Set;

public record UserRequestDto (
        String firstName,
        String lastName,
        String email,
        String password,

        Set<Role> role
){

}

package com.udemyapi.udemyclone.entity;

import java.util.Set;

public record UserMapper(
         String firstname,
         String lastname,
         Set<Role> roleSet

) {
}

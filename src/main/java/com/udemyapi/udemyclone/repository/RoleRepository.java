package com.udemyapi.udemyclone.repository;

import com.udemyapi.udemyclone.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Role findByName(String roleUser);
}

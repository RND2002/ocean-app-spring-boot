package com.udemyapi.udemyclone.repository;

import com.udemyapi.udemyclone.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByEmail(String username);
}

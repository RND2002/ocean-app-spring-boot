package com.udemyapi.udemyclone.repository;

import com.udemyapi.udemyclone.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository extends JpaRepository<Resource, Integer> {
}

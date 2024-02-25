package com.udemyapi.udemyclone.repository;

import com.udemyapi.udemyclone.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Integer> {

}

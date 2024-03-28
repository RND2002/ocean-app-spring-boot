package com.udemyapi.udemyclone.repository;

import com.udemyapi.udemyclone.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course,Integer> {
    List<Course> findAllById(Integer id);

    //List<Course> findAllByUsersId(Integer id);
}

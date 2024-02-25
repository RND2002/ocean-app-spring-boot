package com.udemyapi.udemyclone.repository;

import com.udemyapi.udemyclone.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,Integer> {
}

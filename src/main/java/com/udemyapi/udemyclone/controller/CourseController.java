package com.udemyapi.udemyclone.controller;

import com.udemyapi.udemyclone.entity.Course;
import com.udemyapi.udemyclone.entity.CourseRequestDto;
import com.udemyapi.udemyclone.entity.CourseResponseDto;
import com.udemyapi.udemyclone.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/course")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping("/create")
    public ResponseEntity<String> createCourse( @Valid @RequestBody CourseRequestDto courseDto){
        return courseService.createCourse(courseDto);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<Course>> retrieveAllCourses(){
        return courseService.retrieveAllCourses();
    }

    @GetMapping("get/{courseId}")
    public ResponseEntity<CourseResponseDto> retrieveCourseById(Integer courseId){
        return courseService.retrieveCourseById(courseId);
    }

    @PutMapping("/update/{courseId}")
    public ResponseEntity<Course> updateCourse( @PathVariable Integer courseId,@RequestBody Course course){
        return courseService.updateCourse(courseId,course);
    }

    @DeleteMapping("delete/{courseId}")
    public ResponseEntity<String> deleteCourseById(@PathVariable Integer courseId){
        return courseService.deleteCourseById(courseId);
    }
}

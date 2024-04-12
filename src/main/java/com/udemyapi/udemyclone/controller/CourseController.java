package com.udemyapi.udemyclone.controller;

import com.udemyapi.udemyclone.entity.Course;
import com.udemyapi.udemyclone.entity.CourseRequestDto;
import com.udemyapi.udemyclone.entity.CourseResponseDto;
import com.udemyapi.udemyclone.exception.InvalidRequestException;
import com.udemyapi.udemyclone.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/course")
@RequiredArgsConstructor
@Tag(name = "Course-controller-api",description = "performs crud operation on Course")
public class CourseController {

    private static final Logger logger= LoggerFactory.getLogger(CourseController.class);
    private final CourseService courseService;

    @PostMapping("/create")
    @Operation(summary = "For Creation of Course ")
    public ResponseEntity<String> createCourse(@Valid @ModelAttribute CourseRequestDto courseDto, @RequestParam("data")MultipartFile file) throws IOException {

          try{
              logger.info("Request Received to Create a course with coursedto {}",courseDto);
              return courseService.createCourse(courseDto,file);
          }catch (InvalidRequestException e){
              logger.error("Invalid Request Received for Course Creation {} {}",courseDto,e.getMessage());
              return new ResponseEntity<>("Invalid Request Received for course creation ", HttpStatus.BAD_REQUEST);
          }catch (Exception e){
              logger.error("Internal Server Occurred {}",e.getMessage());
              return new ResponseEntity<>("Internal server error",HttpStatus.INTERNAL_SERVER_ERROR);
          }

    }

    @GetMapping("/get/all")
    @Operation(summary = "For retrieving all Courses")
    public ResponseEntity<List<Course>> retrieveAllCourses(){
        try{
            logger.info("Request received to  retreive all Courses");
            return courseService.retrieveAllCourses();
        }catch (Exception e){
            logger.error("Internal Server Occurred {}",e.getMessage());
            return new ResponseEntity<>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("get/{courseId}")
    @Operation(summary = "For retrieving course by course-id")
    public ResponseEntity<CourseResponseDto> retrieveCourseById(@PathVariable Integer courseId){
        try{
            logger.info("Request received to retreive course by course-id {}",courseId);
            return courseService.retrieveCourseById(courseId);
        }catch (InvalidRequestException invalidRequestException){
            logger.error("Invalid Course-id is provided {}", invalidRequestException.getMessage());
            return new ResponseEntity<>(CourseResponseDto.withErrorMessage(invalidRequestException.getMessage()),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            logger.error("Internal Server Occurred {}",e.getMessage());
            return new ResponseEntity<>( CourseResponseDto.withErrorMessage(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{username}")
    @Operation(summary = "For retrieving all Course associated to a user")
    public ResponseEntity<List<CourseResponseDto>> retrieveAllCoursesByUser(@PathVariable String username){
        try{
            logger.info("Received a request to retrieve all Course associated to a user {}",username);
            return courseService.retrieveAllCoursesByUsername(username);
        }catch (InvalidRequestException invalidRequestException){
            logger.error("Invalid user email -id is received {}",invalidRequestException.getMessage());
            return new ResponseEntity<>(new ArrayList<>(CourseResponseDto.withErrorMessage(invalidRequestException.getMessage()).id()),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            logger.error("Internal Server Occurred {}",e.getMessage());
            return new ResponseEntity<>( new ArrayList<>(CourseResponseDto.withErrorMessage(e.getMessage()).id()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{courseId}")
    @Operation(summary = "For updating Course Object")
    public ResponseEntity<Course> updateCourse( @PathVariable Integer courseId,@RequestBody Course course){
        try{
            logger.info("Request received to update Course with course id {} and Course {}",courseId,course);
            return courseService.updateCourse(courseId,course);
        }catch (InvalidRequestException e){
            logger.error("Invalid courseid or course is received {}",e.getMessage());
            return new ResponseEntity<>(new Course(),HttpStatus.BAD_REQUEST);
        }catch(Exception e){
            logger.error("Internal server error {}",e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(new Course(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("delete/{courseId}")
    public ResponseEntity<String> deleteCourseById(@PathVariable Integer courseId){
        return courseService.deleteCourseById(courseId);
    }
}

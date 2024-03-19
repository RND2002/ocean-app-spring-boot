package com.udemyapi.udemyclone.service;

import com.udemyapi.udemyclone.entity.Author;
import com.udemyapi.udemyclone.entity.Course;
import com.udemyapi.udemyclone.entity.CourseRequestDto;
import com.udemyapi.udemyclone.entity.CourseResponseDto;
import com.udemyapi.udemyclone.repository.AuthorRepository;
import com.udemyapi.udemyclone.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    private final AuthorRepository authorRepository;
    public ResponseEntity<String> createCourse(CourseRequestDto courseDto) {
        System.out.println("In course service");
       Course course=toCourse(courseDto);
       courseRepository.save(course);
       return new ResponseEntity<>("Course has been created successfully",HttpStatus.OK);
    }

    public Course toCourse(CourseRequestDto courseRequestDto){
      Course course=new Course();
      course.setTitle(courseRequestDto.title());
      course.setTags(course.getTags());
      course.setDescription(courseRequestDto.description());
      int lenAuthors=courseRequestDto.authorsId().size();
      for(int i=0;i<lenAuthors;i++){
          Author author=authorRepository.findById(courseRequestDto.authorsId().get(i)).orElseThrow();
          List<Course> courses=author.getCourses();
          courses.add(course);
          author.setCourses(courses);
          authorRepository.save(author);
      }
      course.setCreatedAt(LocalDateTime.now());
      //todo->take care of section here in future

        return course;

    }

    public ResponseEntity<CourseResponseDto> retrieveCourseById( Integer courseId) {

        Course course =courseRepository.findById(courseId).orElseThrow();

        return new ResponseEntity<>(toCourseResponseDto(course),HttpStatus.OK);
    }

    private CourseResponseDto toCourseResponseDto(Course course){
        return new CourseResponseDto(
                course.getTitle(),
                course.getDescription(),
                course.getTags()
                );
    }

    public ResponseEntity<Course> updateCourse(Integer courseId, Course course) {

        Course course1=courseRepository.findById(courseId).orElseThrow();
        course1.setAuthors(course.getAuthors());
        course1.setDescription(course.getDescription());
        course1.setTags(course.getTags());
        course1.setTitle(course.getTitle());
        course1.setSections(course.getSections());
        course1.setUpdatedAt(LocalDateTime.now());
        courseRepository.save(course1);
        return new ResponseEntity<>(course1,HttpStatus.OK);
    }

    public ResponseEntity<String> deleteCourseById(Integer  id) {
        Course course=courseRepository.findById(id).orElseThrow();
        courseRepository.delete(course);
        return new ResponseEntity<>("Course deleted Successfully",HttpStatus.OK);
    }

    public ResponseEntity<List<Course>> retrieveAllCourses() {
        List<Course> courses=courseRepository.findAll();
        return new ResponseEntity<>(courses,HttpStatus.OK);
    }
}

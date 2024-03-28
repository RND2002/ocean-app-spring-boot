package com.udemyapi.udemyclone.service;

import com.sun.jdi.request.InvalidRequestStateException;
import com.udemyapi.udemyclone.entity.*;
import com.udemyapi.udemyclone.repository.AuthorRepository;
import com.udemyapi.udemyclone.repository.CourseRepository;
import com.udemyapi.udemyclone.repository.RoleRepository;
import com.udemyapi.udemyclone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    private final AuthorRepository authorRepository;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;
    public ResponseEntity<String> createCourse(CourseRequestDto courseDto, MultipartFile file) throws IOException {
        List<Integer> authors=courseDto.authors();
        if(authors.size()==0){
            return new ResponseEntity<>("No author is associated",HttpStatus.BAD_REQUEST);
        }
        boolean isAuthor = false;

       for(int i=0;i<authors.size();i++){
           try{
               User user=userRepository.findById(authors.get(i)).orElseThrow();
               Set<Role> roles=user.getRoles();
               for (Role role : roles) {
                   if (role.getName().equals("ROLE-AUTHOR")) {
                       isAuthor = true;

                       break;
                   }
               }
           }catch (InvalidRequestStateException e){
               e.printStackTrace();
               return new ResponseEntity<>("User has no rights of Author",HttpStatus.BAD_REQUEST);
           }
       }

        //System.out.println("In course service");
       Course course=toCourse(courseDto);
     //  course.setTags(courseDto.tags());
        byte [] imageData= file.getBytes();
        course.setImage(imageData);
       courseRepository.save(course);
       return new ResponseEntity<>("Course has been created successfully",HttpStatus.OK);
    }

    public Course toCourse(CourseRequestDto courseRequestDto){
      Course course=new Course();
      course.setTitle(courseRequestDto.title());
      course.setTags(courseRequestDto.tags());
      course.setImage(courseRequestDto.image());
      course.setDescription(courseRequestDto.description());
      List<User> auhtors=new ArrayList<>();
      int lenAuthors=courseRequestDto.authors().size();
      for(int i=0;i<lenAuthors;i++){
          User user=userRepository.findById(courseRequestDto.authors().get(i)).orElseThrow();
          List<Course> courses=user.getCourses();
          courses.add(course);
          auhtors.add(user);
          user.setCourses(courses);

          userRepository.save(user);
      }
      course.setUsers(auhtors);
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
                course.getId(),
                course.getTitle(),
                course.getDescription(),
                course.getImage(),
                course.getTags()
                );
    }

    public ResponseEntity<Course> updateCourse(Integer courseId, Course course) {

        Course course1=courseRepository.findById(courseId).orElseThrow();
        course1.setUsers(course.getUsers());
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

//    public ResponseEntity<List<CourseResponseDto>> retrieveAllCoursesByUsername(String username) {
//        User user=userRepository.findByEmail(username);
//        if(user==null){
//
//            return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
//        }
//        Integer userId= user.getId();
//        List<Course> courses=courseRepository.findAllById(userId);
//       // System.out.println(courses);
//        List<CourseResponseDto> coursesDto=new ArrayList<>();
//        for(int i=0;i<courses.size();i++){
//            coursesDto.add(toCourseResponseDto(courses.get(i)));
//        }
//        return new ResponseEntity<>(coursesDto,HttpStatus.OK);
//    }
public ResponseEntity<List<CourseResponseDto>> retrieveAllCoursesByUsername(String username) {
    User user = userRepository.findByEmail(username);
    if (user == null) {
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }
    List<Course> courses=user.getCourses();


    List<CourseResponseDto> coursesDto = new ArrayList<>();
    for (Course course : courses) {
        coursesDto.add(toCourseResponseDto(course));
    }

    return new ResponseEntity<>(coursesDto, HttpStatus.OK);
}

}

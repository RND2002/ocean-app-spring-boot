package com.udemyapi.udemyclone.service;

import com.udemyapi.udemyclone.entity.*;
import com.udemyapi.udemyclone.repository.CourseRepository;
import com.udemyapi.udemyclone.repository.LectureRepository;
import com.udemyapi.udemyclone.repository.SectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SectionService {

    private final SectionRepository sectionRepository;

    private final CourseRepository courseRepository;
    private final LectureRepository lectureRepository;

    public ResponseEntity<String> createSection(Integer courseId, SectionRequestDto requestDto){
        sectionRepository.save(sectionDtoToSection(courseId,requestDto));
        return new ResponseEntity<>("Section added successfully", HttpStatus.OK);
    }

    public Section sectionDtoToSection(Integer courseId,SectionRequestDto requestDto){
        Section section=new Section();
        section.setName(requestDto.name());
        section.setSectionOrder(requestDto.sectionOrder());

        section.setCreatedAt(LocalDateTime.now());
        Course course=courseRepository.findById(courseId).orElseThrow();
        section.setCourse(course);
        if(course.getSections().size()!=0){
            List<Section> sections=course.getSections();
            sections.add(section);
            course.setSections(sections);
        }else{
            ArrayList<Section> sections=new ArrayList<>();
            sections.add(section);
            course.setSections(sections);

        }
        courseRepository.save(course);
        ArrayList<Lecture> lectures=new ArrayList<>();
//        for(int i=0;i<requestDto.lectureIds().size();i++){
//            Lecture lecture=new Lecture();
//            lecture.setSection(section);
//            lectures.add(lecture);
//        }
//        section.setLectures(lectures);

        return section;

    }

    public SectionResponseDto sectionResponseDto(Section section){
        return new SectionResponseDto(
                section.getId(),
                section.getName(),
                section.getSectionOrder()
               // section.getLectures()
        );
    }

    public ResponseEntity<List<SectionResponseDto>> retrieveSections(Integer courseId) {
        System.out.println(courseId);
        List<Section> sections=sectionRepository.findAllByCourseId(courseId);
        //List<Lecture> lectures=new ArrayList<>();
//        for(int i=0;i<sections.size();i++){
//            List<Lecture> lectures=lectureRepository(sections.get(i).getLectures())
//            lectures.add()
//        }
        List<SectionResponseDto> responseDto=new ArrayList<>();
        for(int i=0;i<sections.size();i++){
            responseDto.add(sectionResponseDto(sections.get(i)));
        }
        System.out.println(responseDto);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);

    }



    public ResponseEntity<SectionResponseDto> retrieveSectionById(Integer courseId, Integer sectionId) {

        Section section=sectionRepository.findAllByCourseIdAndId(courseId,sectionId);
        return new ResponseEntity<>(sectionResponseDto(section),HttpStatus.OK);
    }

    public ResponseEntity<String> deleteSectionById(Integer sectionId) {
        Section section=sectionRepository.findById(sectionId).orElseThrow();
        sectionRepository.delete(section);
        return new ResponseEntity<>("DeletedSuccessfully",HttpStatus.OK);
    }

//    public ResponseEntity<CourseResponseDto> updateSection(Integer courseId, Integer sectionId) {
//    }
}

package com.udemyapi.udemyclone.service;

import com.udemyapi.udemyclone.entity.*;
import com.udemyapi.udemyclone.repository.LectureRepository;
import com.udemyapi.udemyclone.repository.ResourceRepository;
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
public class LectureService {

    private final LectureRepository lectureRepository;
    private final SectionRepository sectionRepository;
    private final ResourceRepository resourceRepository;

    public ResponseEntity<String> createLecture(

                                              Integer sectionId,  LectureRequestDto lectureRequestDto) {
        Lecture lecture=toLecture(sectionId,lectureRequestDto);
        lectureRepository.save(lecture);
        return new ResponseEntity<>("Lecture saved successfully", HttpStatus.CREATED);

    }

//    private Lecture toLecture(
//                              LectureRequestDto lectureRequestDto){
//        Lecture lecture=new Lecture();
//        lecture.setName(lectureRequestDto.name());
//        lecture.setCreatedAt(LocalDateTime.now());
//        lecture.setName(lectureRequestDto.name());
//        Section section=sectionRepository.findById(lectureRequestDto.sectionId()).orElseThrow();
//        lecture.setSection(section);
//        //todo take care of resources
//        Resource resource=new Resource();
//        resource.setName(lectureRequestDto.resource().getName());
//        resource.setLecture(lecture);
//        resource.setUrl(lectureRequestDto.resource().getUrl());
//        resource.setSize(lectureRequestDto.resource().getSize());
//        resourceRepository.save(resource);
//        lecture.setResource(resource);
//        return lecture;
//    }
private Lecture toLecture(Integer sectionId,LectureRequestDto lectureRequestDto) {
    Lecture lecture = new Lecture();
    lecture.setName(lectureRequestDto.name());
    lecture.setCreatedAt(LocalDateTime.now());

    Section section = sectionRepository.findById(sectionId).orElseThrow();
    lecture.setSection(section);

    // Create and set resource for the lecture
    Resource resource = new Resource();
    resource.setName(lectureRequestDto.resource().getName());
    resource.setUrl(lectureRequestDto.resource().getUrl());
    resource.setSize(lectureRequestDto.resource().getSize());

    // Save the lecture first to ensure it has an ID before setting it to the resource
    Lecture savedLecture = lectureRepository.save(lecture);

    // Set the lecture to the resource and save the resource
    resource.setLecture(savedLecture);
    resourceRepository.save(resource);

    // Set the resource to the lecture
    savedLecture.setResource(resource);

    return savedLecture;
}


    private LectureResponseDto toLectureResponseDto(Lecture lecture){
        return new LectureResponseDto(
                lecture.getId(),
                lecture.getName(),
                lecture.getResource(),
                null
        );
    }

    public ResponseEntity<List<LectureResponseDto>> retrieveAllLecturesOfASection(Integer sectionId){
        List<Lecture> lectures=lectureRepository.findAllBySectionId(sectionId);
        int lectureSize= lectures.size();
        List<LectureResponseDto> responseDtoList=new ArrayList<>();
        for(int i=0;i<lectureSize;i++){
            responseDtoList.add(toLectureResponseDto(lectures.get(i)));
        }
        return new ResponseEntity<>(responseDtoList,HttpStatus.OK);
    }

    public ResponseEntity<LectureResponseDto> retrieveLectureById(Integer sectionId, Integer lectureId){
        Lecture lecture=lectureRepository.findAllBySectionIdAndId(sectionId,lectureId);
        return new ResponseEntity<>(toLectureResponseDto(lecture),HttpStatus.OK);
    }


    public ResponseEntity<String> deleteLectureById(Integer sectionId, Integer lectureId) {

        Lecture lecture=lectureRepository.findAllBySectionIdAndId(sectionId,lectureId);
        lectureRepository.delete(lecture);
        return new ResponseEntity<>("Deleted a lecture successfully",HttpStatus.OK);
    }
}

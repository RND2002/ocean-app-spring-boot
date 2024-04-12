package com.udemyapi.udemyclone.service;

import com.udemyapi.udemyclone.entity.Lecture;
import com.udemyapi.udemyclone.entity.Resource;
import com.udemyapi.udemyclone.repository.LectureRepository;
import com.udemyapi.udemyclone.repository.ResourceRepository;
import com.udemyapi.udemyclone.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
@RequiredArgsConstructor
public class ResourceService {

    private ResourceRepository resourceRepository;
    private LectureRepository lectureRepository;

    private VideoRepository videoRepository;
    public ResponseEntity<String> addResourceByLectureId(Integer lectureId,Resource resource){
        Lecture lecture=lectureRepository.findById(lectureId).orElseThrow();
        lecture.setResource(resource);
        resource.setLecture(lecture);

        try{
            lectureRepository.save(lecture);
            resourceRepository.save(resource);
            return new ResponseEntity<>("Resource has been added to database", HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("There is issue saving data",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String>updateLectureResource(Integer resourceId,Resource resource){

        try{
            Resource resource1=resourceRepository.findById(resourceId).orElseThrow();
            resource1.setName(resource.getName());
            resource1.setSize(resource.getSize());
            resource1.setLecture(resource.getLecture());
            resource1.setUrl(resource.getUrl());
            return new ResponseEntity<>("Resource has been updated successfully",HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Error occurred while updating resource",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

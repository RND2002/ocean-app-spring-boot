//package com.udemyapi.udemyclone.service;
//
//import com.udemyapi.udemyclone.entity.Lecture;
//import com.udemyapi.udemyclone.entity.Resource;
//import com.udemyapi.udemyclone.entity.Video;
//import com.udemyapi.udemyclone.repository.LectureRepository;
//import com.udemyapi.udemyclone.repository.VideoRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class VideoService {
//
//    private VideoRepository videoRepository;
//    private LectureRepository lectureRepository;
//    public ResponseEntity<String> addVideoToRepo(Integer lectureId,Video video){
//        try{
//            Lecture lecture=lectureRepository.findById(lectureId).orElseThrow();
//            video.setLecture(lecture);
//            Resource resource=new Resource();
//            resource.
//        }
//    }
//
//}

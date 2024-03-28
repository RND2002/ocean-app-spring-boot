package com.udemyapi.udemyclone.controller;

import com.udemyapi.udemyclone.entity.LectureRequestDto;
import com.udemyapi.udemyclone.entity.LectureResponseDto;
import com.udemyapi.udemyclone.service.LectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/sections")
@RequiredArgsConstructor
public class LectureController {

    private final LectureService lectureService;


    @PostMapping("/lectures")
    public ResponseEntity<String> createLecture(
                                                @RequestBody LectureRequestDto lectureRequestDto){
        return lectureService.createLecture(lectureRequestDto);
    }

    @GetMapping("/sections/{sectionId}/lectures")
    public ResponseEntity<List<LectureResponseDto>> retrieveAllLecturesOfASection(@PathVariable Integer sectionId){
        return lectureService.retrieveAllLecturesOfASection(sectionId);
    }

    @GetMapping("/sections/{sectionId}/lectures/{lectureId}")
    public ResponseEntity<LectureResponseDto> retrieveLectureByLectureId(@PathVariable Integer sectionId,@PathVariable Integer lectureId){
        return lectureService.retrieveLectureById(sectionId,lectureId);
    }

    @DeleteMapping("/sectoins/{sectionId}/lectures/{lectureId}")
    public ResponseEntity<String> deleteLectureById(Integer sectionId,Integer lectureId){
        return lectureService.deleteLectureById(sectionId,lectureId);
    }
}

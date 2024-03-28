package com.udemyapi.udemyclone.controller;

import com.udemyapi.udemyclone.entity.CourseResponseDto;
import com.udemyapi.udemyclone.entity.Section;
import com.udemyapi.udemyclone.entity.SectionRequestDto;
import com.udemyapi.udemyclone.entity.SectionResponseDto;
import com.udemyapi.udemyclone.service.SectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
public class SectionController {

    private final SectionService sectionService;

    @PostMapping("/{courseId}/sections")
    public ResponseEntity<String> createSection(@PathVariable Integer courseId, @RequestBody SectionRequestDto sectionRequestDto){
        return sectionService.createSection(courseId,sectionRequestDto);
    }

    @GetMapping("/{courseId}/sections")
    public ResponseEntity<List<SectionResponseDto>> retrieveSections(Integer courseId){
        return sectionService.retrieveSections(courseId);
    }

    @GetMapping("/{courseId}/sections/{sectionId}")
    public ResponseEntity<SectionResponseDto> retrieveSectionById(@PathVariable Integer courseId,@PathVariable Integer sectionId){
        return sectionService.retrieveSectionById(courseId,sectionId);
    }

//    @PutMapping("/{courseId}/sections/{sectionId}")
//    public ResponseEntity<CourseResponseDto> updateSection(@PathVariable Integer courseId,@PathVariable Integer sectionId,SectionRequestDto sectionRequestDto){
//        return sectionService.updateSection(courseId,sectionId,sectionRequestDto);
//    }
}

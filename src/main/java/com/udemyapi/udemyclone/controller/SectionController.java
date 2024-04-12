package com.udemyapi.udemyclone.controller;

import com.udemyapi.udemyclone.entity.CourseResponseDto;
import com.udemyapi.udemyclone.entity.Section;
import com.udemyapi.udemyclone.entity.SectionRequestDto;
import com.udemyapi.udemyclone.entity.SectionResponseDto;
import com.udemyapi.udemyclone.exception.InvalidRequestException;
import com.udemyapi.udemyclone.service.SectionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
@Tag(name = "Section Controller",description = "Performs crud operation on Sections of Course")
public class SectionController {

    private static final Logger logger= LoggerFactory.getLogger(SectionController.class);
    private final SectionService sectionService;

    @PostMapping("/{courseId}/sections")
    @Operation(summary = "For Creating a Section of Course")
    public ResponseEntity<String> createSection(@PathVariable Integer courseId, @RequestBody SectionRequestDto sectionRequestDto){
        try{
            logger.info("Request received to Create a Section of a Course with Course id {} and Request dto {}",courseId,sectionRequestDto);
            return sectionService.createSection(courseId,sectionRequestDto);
        }catch(InvalidRequestException e){
            logger.error("Invalid Course id or Section Request dto received {}",e.getMessage());
            return new ResponseEntity<>("Invalid Request Received", HttpStatus.BAD_REQUEST);
        }catch(Exception e){
            logger.error("Internal Server error {}",e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>("Internal Server error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{courseId}/sections")
    @Operation(summary = "For Retreiving Section of a Course")
    public ResponseEntity<List<SectionResponseDto>> retrieveSections(@PathVariable Integer courseId){
        try{
            logger.info("Received a request to retrieve sections of a course {}",courseId);
            return sectionService.retrieveSections(courseId);
        }catch(InvalidRequestException e){
            logger.error("Invalid Course id is present {}",e.getMessage());
            return new ResponseEntity<>(new ArrayList<>(SectionResponseDto.withErrorMessage(e.getMessage()).sectionId()),HttpStatus.BAD_REQUEST);
        }catch(Exception e){
            logger.error("Internal Server error {}",e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(SectionResponseDto.withErrorMessage(e.getMessage()).sectionId()),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{courseId}/sections/{sectionId}")
    @Operation(summary = "For Retrieving a Section by specific Section id")
    public ResponseEntity<SectionResponseDto> retrieveSectionById(@PathVariable Integer courseId,@PathVariable Integer sectionId){
        try{
            logger.info("Request received to retrieve Section by Course id {} and Section ID {}",courseId,sectionId);
            return sectionService.retrieveSectionById(courseId,sectionId);
        }catch(InvalidRequestException e){
            logger.error("Invalid Course id is present {} or section id",e.getMessage());
            return new ResponseEntity<>(SectionResponseDto.withErrorMessage(e.getMessage()),HttpStatus.BAD_REQUEST);
        }catch(Exception e){
            logger.error("Internal Server error {}",e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(SectionResponseDto.withErrorMessage(e.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }

//    @PutMapping("/{courseId}/sections/{sectionId}")
//    public ResponseEntity<CourseResponseDto> updateSection(@PathVariable Integer courseId,@PathVariable Integer sectionId,SectionRequestDto sectionRequestDto){
//        return sectionService.updateSection(courseId,sectionId,sectionRequestDto);
//    }

    @DeleteMapping("/{sectionId}/sections")
    public ResponseEntity<String> deleteSection(@PathVariable Integer sectionId){
        return sectionService.deleteSectionById(sectionId);
    }
}

package com.udemyapi.udemyclone.controller;

import com.udemyapi.udemyclone.entity.LectureRequestDto;
import com.udemyapi.udemyclone.entity.LectureResponseDto;
import com.udemyapi.udemyclone.exception.InvalidRequestException;
import com.udemyapi.udemyclone.service.LectureService;
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
@RequestMapping("api/v1/sections")
@RequiredArgsConstructor
@Tag(name = "Lecture Controller",description = "Associates Lecture with Sections and crud operation")
public class LectureController {

    private final LectureService lectureService;
    private static final Logger logger= LoggerFactory.getLogger(LectureController.class);


    @PostMapping("/{sectionId}/lectures")
    @Operation(summary = "For Creating Lecture")
    public ResponseEntity<String> createLecture(
                                               @PathVariable Integer sectionId, @RequestBody LectureRequestDto lectureRequestDto){
       try{
           logger.info("Received a request to Create Lecture with Section id {} and Request dto {}",sectionId,lectureRequestDto);
           return lectureService.createLecture(sectionId,lectureRequestDto);
       }catch(InvalidRequestException e){
           logger.error("Invalid section id or Section Request dto received {}",e.getMessage());
           return new ResponseEntity<>("Invalid Request Received", HttpStatus.BAD_REQUEST);
       }catch(Exception e){
           logger.error("Internal Server error {}",e.getMessage());
           e.printStackTrace();
           return new ResponseEntity<>("Internal Server error",HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    @GetMapping("/{sectionId}/lectures")
    @Operation(summary = "For retrieving all lectures of a section")
    public ResponseEntity<List<LectureResponseDto>> retrieveAllLecturesOfASection(@PathVariable Integer sectionId){

        try{
            logger.info("Received a request to get Lecture with Section id {}",sectionId);
            return lectureService.retrieveAllLecturesOfASection(sectionId);
        }catch(InvalidRequestException e){
            logger.error("Invalid section id received {}",e.getMessage());
            return new ResponseEntity<>(new ArrayList<>(LectureResponseDto.withErrorMessage(e.getMessage()).id()), HttpStatus.BAD_REQUEST);
        }catch(Exception e){
            logger.error("Internal Server error {}",e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(LectureResponseDto.withErrorMessage(e.getMessage()).id()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sections/{sectionId}/lectures/{lectureId}")
    @Operation(summary = "Retreiving lecture by lecture id")
    public ResponseEntity<LectureResponseDto> retrieveLectureByLectureId(@PathVariable Integer sectionId,@PathVariable Integer lectureId){

        try{
            logger.info("Received a request to Create Lecture with Section id {} and lecture id{}",sectionId,lectureId);
            return lectureService.retrieveLectureById(sectionId,lectureId);
        }catch(InvalidRequestException e){
            logger.error("Invalid section id or Section Request dto received {}",e.getMessage());
            return new ResponseEntity<>(LectureResponseDto.withErrorMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
        }catch(Exception e){
            logger.error("Internal Server error {}",e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(LectureResponseDto.withErrorMessage(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/sectoins/{sectionId}/lectures/{lectureId}")
    public ResponseEntity<String> deleteLectureById(Integer sectionId,Integer lectureId){
        return lectureService.deleteLectureById(sectionId,lectureId);
    }
}

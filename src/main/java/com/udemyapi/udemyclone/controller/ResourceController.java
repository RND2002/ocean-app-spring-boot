package com.udemyapi.udemyclone.controller;

import com.udemyapi.udemyclone.entity.Resource;
import com.udemyapi.udemyclone.service.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/lecture")
public class ResourceController {

    private ResourceService resourceService;
    @PostMapping("/{lectureId}/resource")
    public ResponseEntity<String> saveResource(@PathVariable Integer lectureId,@RequestBody Resource resource){
        return resourceService.addResourceByLectureId(lectureId,resource);
    }

    public ResponseEntity<String> updateResource(@PathVariable Integer resourceId,@RequestBody Resource resource){
        return resourceService.updateLectureResource(resourceId,resource);
    }
}

package com.udemyapi.udemyclone.entity;

import java.util.List;

public record SectionRequestDto(
        String name,
        int sectionOrder

       // Integer courseId,

        //List<Integer> lectureIds
) {
}

package com.udemyapi.udemyclone.entity;

import java.util.List;

public record CourseRequestDto(
        String title,
        String description,
        String tags,
        List<Integer> authorsId,
        List<Integer> sections

) {


}

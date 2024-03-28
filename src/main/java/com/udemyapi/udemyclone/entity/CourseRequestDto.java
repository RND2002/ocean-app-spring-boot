package com.udemyapi.udemyclone.entity;

import java.util.List;

public record CourseRequestDto(
        String title,
        String description,
        String tags,

        byte [] image,
        List<Integer> authors,
        List<Integer> sections

) {


}

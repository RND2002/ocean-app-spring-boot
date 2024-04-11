package com.udemyapi.udemyclone.entity;

public record CourseResponseDto(
        Integer id,
        String title,
        String description,

        byte[] image,
        String tags

) {
}

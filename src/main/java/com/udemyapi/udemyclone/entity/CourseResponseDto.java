package com.udemyapi.udemyclone.entity;

public record CourseResponseDto(
        Integer id,
        String title,
        String tags,
        byte[] image,
        String description
) {
}

package com.udemyapi.udemyclone.entity;

public record LectureRequestDto(
        String name,
        Integer sectionId,
        Integer resourceId
) {
}

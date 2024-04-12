package com.udemyapi.udemyclone.entity;

import java.util.List;

public record SectionResponseDto(
        Integer sectionId,
        String name,
        int sectionOrder,
        String errorMessage

        //List<Lecture> lectures
) {
    public static SectionResponseDto withErrorMessage(String errorMessage) {
        return new SectionResponseDto(null, null, 0, errorMessage);
    }
}

package com.udemyapi.udemyclone.entity;

public record LectureResponseDto(
        Integer id,
        String name,


        Resource resource,
        String errorMessage
) {
    public static LectureResponseDto withErrorMessage(String errorMessage) {
        return new LectureResponseDto(null, null,null, errorMessage);
    }
}

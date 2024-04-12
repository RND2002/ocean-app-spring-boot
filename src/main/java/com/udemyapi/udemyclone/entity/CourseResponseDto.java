package com.udemyapi.udemyclone.entity;

public record CourseResponseDto(
        Integer id,
        String title,
        String description,

        byte[] image,
        String tags,
        String errorMessage

) {
    public static CourseResponseDto withErrorMessage(String errorMessage) {
        return new CourseResponseDto(null, null, null, null,null, errorMessage);
    }
}

package com.udemyapi.udemyclone.entity;

import java.util.List;

public record SectionResponseDto(
        Integer sectionId,
        String name,
        int sectionOrder,

        List<Lecture> lectures
) {
}

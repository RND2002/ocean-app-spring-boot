package com.udemyapi.udemyclone.repository;

import com.udemyapi.udemyclone.entity.Section;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface SectionRepository extends JpaRepository<Section,Integer> {
    List<Section> findAllByCourseId(Integer courseId);

    Section findAllByCourseIdAndId(Integer courseId, Integer sectionId);
}

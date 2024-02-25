package com.udemyapi.udemyclone.repository;

import com.udemyapi.udemyclone.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectureRepository extends JpaRepository<Lecture,Integer> {
   // List<Lecture> findAllByCourseId(Integer courseId, Integer sectionId);

    List<Lecture> findAllBySectionId(Integer sectionId);

    Lecture findAllBySectionIdAndId(Integer sectionId, Integer lectureId);
}

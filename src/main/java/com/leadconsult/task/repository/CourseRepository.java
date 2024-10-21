package com.leadconsult.task.repository;

import com.leadconsult.task.constant.CourseType;
import com.leadconsult.task.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

	Long countAllByType(CourseType type);
}

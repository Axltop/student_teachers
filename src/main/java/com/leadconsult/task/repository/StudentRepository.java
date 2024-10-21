package com.leadconsult.task.repository;

import com.leadconsult.task.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

	List<Student> findAllByCoursesCourseId(Long courseId);

	List<Student> findAllByGroupsGroupId(Long groupId);

	List<Student> findAllByAgeGreaterThanEqualAndCoursesCourseIdIs(Short age, Long courseId);
}
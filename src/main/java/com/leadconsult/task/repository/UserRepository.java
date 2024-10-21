package com.leadconsult.task.repository;

import com.leadconsult.task.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

	List<User> findAllByGroupsGroupIdAndCoursesCourseId(Long groupId,Long courseId);

}

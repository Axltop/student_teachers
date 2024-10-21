package com.leadconsult.task.repository;

import com.leadconsult.task.constant.UserType;
import com.leadconsult.task.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findAllByGroupsGroupIdAndCoursesCourseId(Long groupId,Long courseId);

	List<User> findAllByCoursesCourseIdAndUserType(Long courseId, UserType userType);

	List<User> findAllByGroupsGroupIdAndUserType(Long groupId, UserType userType);

	List<User> findAllByAgeGreaterThanEqualAndCoursesCourseIdIsAndUserType(Short age, Long courseId, UserType userType);

	Long countAllByUserType(UserType userType);
}

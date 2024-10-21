package com.leadconsult.task.service;

import com.leadconsult.task.constant.UserType;
import com.leadconsult.task.exception.UserNotFound;
import com.leadconsult.task.model.User;
import com.leadconsult.task.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
	private final UserRepository userRepository;

	public UserService(final UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public List<User> findAllByGroupIdAndCourseId(Long groupId, Long courseId) {
		return userRepository.findAllByGroupsGroupIdAndCoursesCourseId(groupId, courseId);
	}

	public User update(User user) throws UserNotFound {

		Optional<User> existingStudentOptional = userRepository.findById(user.getUserId());
		if (existingStudentOptional.isPresent()) {
			User existingStudent = existingStudentOptional.get();
			BeanUtils.copyProperties(user, existingStudent, new String[]{"userId", "userType"});
			return userRepository.save(existingStudent);
		} else {
			throw new UserNotFound( user.getUserId());
		}
	}

	public User getById(Long userId) throws UserNotFound {
		Optional<User> existingStudentOptional = userRepository.findById(userId);
		if (existingStudentOptional.isPresent()) {
			return userRepository.findById(userId).orElseThrow();
		} else {
			throw new UserNotFound( userId);
		}
	}

	public void deleteById(Long userId) throws UserNotFound {
		Optional<User> existingStudentOptional = userRepository.findById(userId);
		if (existingStudentOptional.isPresent()) {
			userRepository.deleteById(userId);
		} else {
			throw new UserNotFound(userId);
		}

	}

	public Long countAllByUserType(UserType userType){
		return userRepository.countAllByUserType(userType);
	}

	public List<User> findAllByCourseIdAndUserType(Long courseId,UserType userType) {
		return userRepository.findAllByCoursesCourseIdAndUserType(courseId,userType);
	}

	public List<User> findAllByGroupIdAndUserType(Long groupId,UserType userType) {
		return userRepository.findAllByGroupsGroupIdAndUserType(groupId,userType);
	}

	public List<User> findAllOlderThanAndCourseIdAndUserType(Short age, Long courseId, UserType userType) {
		return userRepository.findAllByAgeGreaterThanEqualAndCoursesCourseIdIsAndUserType(age,courseId,userType);
	}

	public User save(User user){
		return userRepository.save(user);
	}
}

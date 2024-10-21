package com.leadconsult.task.service;

import com.leadconsult.task.model.Student;
import com.leadconsult.task.model.User;
import com.leadconsult.task.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
	private final UserRepository userRepository;

	public UserService(final UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public List<User> findAllByGroupIdAndCourseId(Long groupId, Long courseId) {
		return userRepository.findAllByGroupsGroupIdAndCoursesCourseId(groupId,courseId);
	}
}

package com.leadconsult.task.service;

import com.leadconsult.task.exception.UserNotFound;
import com.leadconsult.task.model.Teacher;
import com.leadconsult.task.repository.TeacherRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeacherService {

	private final TeacherRepository teacherRepository;

	public TeacherService(final TeacherRepository teacherRepository) {
		this.teacherRepository = teacherRepository;
	}

	public Teacher save(final Teacher teacher) {
		return teacherRepository.save(teacher);
	}

	public Teacher update(Teacher teacher) throws UserNotFound {

		Optional<Teacher> existingTeacherOptional = teacherRepository.findById(teacher.getUserId());
		if (existingTeacherOptional.isPresent()) {
			Teacher existingStudent = existingTeacherOptional.get();
			BeanUtils.copyProperties(teacher, existingStudent, new String[]{"userId", "userType"});
			return teacherRepository.save(existingStudent);
		} else {
			throw new UserNotFound("Teacher not found with id: " + teacher.getUserId());
		}
	}

	public Teacher getById(Long id) {
		return teacherRepository.findById(id).orElseThrow();
	}

	public void deleteById(Long id) {
		teacherRepository.deleteById(id);
	}

	public Long countAll() {
		return teacherRepository.count();
	}
}

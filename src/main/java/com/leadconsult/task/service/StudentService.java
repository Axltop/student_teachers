package com.leadconsult.task.service;

import com.leadconsult.task.exception.UserNotFound;
import com.leadconsult.task.model.Course;
import com.leadconsult.task.model.Group;
import com.leadconsult.task.model.Student;
import com.leadconsult.task.repository.StudentRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentService {
	private final StudentRepository studentRepository;


	public StudentService(final  StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}


	@PostConstruct
	public void init(){
		Random random = new Random();
		studentRepository.findAll().forEach(student -> {

			Set<Course> courses = new HashSet<>();
			for(int i=0;i <=random.nextInt(5,12); i++){
				courses.add(Course.builder().courseId(random.nextLong(1,12)).build());
			}
			student.setCourses(courses);

			Set<Group> groups = new HashSet<>();
			for(int i=0;i <=random.nextInt(1,5); i++){
				groups.add(Group.builder().groupId(random.nextLong(1,5)).build());
			}
			student.setGroups(groups);

			studentRepository.save(student);
		});
	}

	public Student save(final Student user) {

		return studentRepository.save(user);
	}

	public Student update(Student student) throws UserNotFound {

		Optional<Student> existingStudentOptional = studentRepository.findById(student.getUserId());
		if (existingStudentOptional.isPresent()) {
			Student existingStudent = existingStudentOptional.get();
			BeanUtils.copyProperties(student, existingStudent, new String[]{"userId", "userType"});
			return studentRepository.save(existingStudent);
		} else {
			throw new UserNotFound("Student not found with id: " + student.getUserId());
		}
	}

	public Student getById(Long studentId) {
		return studentRepository.findById(studentId).orElseThrow();
	}

	public void deleteById(Long studentId) {
		studentRepository.deleteById(studentId);
	}

	public Long countAll(){
		return studentRepository.count();
	}

	public List<Student> findAllByCourseId(Long courseId) {
		return studentRepository.findAllByCoursesCourseId(courseId);
	}

	public List<Student> findAllByGroupId(Long groupId) {
		return studentRepository.findAllByGroupsGroupId(groupId);
	}

	public List<Student> findAllOlderThanAndCourseId(Short age, Long courseId) {
		return studentRepository.findAllByAgeGreaterThanEqualAndCoursesCourseIdIs(age,courseId);
	}
}

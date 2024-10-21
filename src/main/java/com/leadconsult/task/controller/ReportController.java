package com.leadconsult.task.controller;

import com.leadconsult.task.ResponseWrapper;
import com.leadconsult.task.constant.CourseType;
import com.leadconsult.task.model.Student;
import com.leadconsult.task.model.User;
import com.leadconsult.task.repository.CourseRepository;
import com.leadconsult.task.service.StudentService;
import com.leadconsult.task.service.TeacherService;
import com.leadconsult.task.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/report")
public class ReportController {
	private final StudentService studentService;
	private final TeacherService teacherService;
	private final CourseRepository courseRepository;
	private final UserService userService;

	public ReportController(final StudentService studentService, final TeacherService teacherService, final CourseRepository courseRepository, UserService userService) {
		this.studentService = studentService;
		this.teacherService = teacherService;
		this.courseRepository = courseRepository;
		this.userService = userService;
	}


	@GetMapping("/student/count")
	public ResponseEntity<ResponseWrapper<Long>> getStudentsCount() {
		return ResponseEntity.ok(new ResponseWrapper<>(studentService.countAll()));
	}

	@GetMapping("/teacher/count")
	public ResponseEntity<ResponseWrapper<Long>> getTeachersCount() {
		return ResponseEntity.ok(new ResponseWrapper<>(teacherService.countAll()));
	}

	@GetMapping("/course/byType")
	public ResponseEntity<ResponseWrapper<Long>> getCoursesCountByType(@RequestParam String type) {
		return ResponseEntity.ok(new ResponseWrapper<>(courseRepository.countAllByType(CourseType.valueOf(type.toUpperCase()))));
	}

	@GetMapping("/student/byCourse")
	public ResponseEntity<ResponseWrapper<List<Student>>> getStudentsByCourseType(@RequestParam Long courseId){
		return ResponseEntity.ok(new ResponseWrapper<>(studentService.findAllByCourseId(courseId)));
	}

	@GetMapping("/student/byGroup")
	public ResponseEntity<ResponseWrapper<List<Student>>> getStudentsByGroup(@RequestParam Long groupId){
		return ResponseEntity.ok(new ResponseWrapper<>(studentService.findAllByGroupId(groupId)));
	}

	@GetMapping("/user/byGroupAndCourse")
	public ResponseEntity<ResponseWrapper<List<User>>> getUsersByGroupAndCourse(@RequestParam Long groupId, @RequestParam Long courseId){
		return ResponseEntity.ok(new ResponseWrapper<>(userService.findAllByGroupIdAndCourseId(groupId,courseId)));
	}

	@GetMapping("/student/byAgeAndCourse")
	public ResponseEntity<ResponseWrapper<List<Student>>> getStudentsByGroup(@RequestParam Short age, @RequestParam Long courseId){
		return ResponseEntity.ok(new ResponseWrapper<>(studentService.findAllOlderThanAndCourseId(age,courseId)));
	}


}

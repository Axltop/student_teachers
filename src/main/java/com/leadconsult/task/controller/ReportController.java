package com.leadconsult.task.controller;

import com.leadconsult.task.constant.CourseType;
import com.leadconsult.task.constant.UserType;
import com.leadconsult.task.helper.ResponseWrapper;
import com.leadconsult.task.model.User;
import com.leadconsult.task.service.CourseService;
import com.leadconsult.task.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/report")
public class ReportController {


	private final UserService userService;
	private final CourseService courseService;

	public ReportController( final UserService userService, final CourseService courseService) {
		this.userService = userService;
		this.courseService = courseService;
	}

	@GetMapping("/student/count")
	public ResponseEntity<ResponseWrapper<Long>> getStudentsCount() {
		return ResponseEntity.ok(new ResponseWrapper<>(userService.countAllByUserType(UserType.STUDENT)));
	}

	@GetMapping("/teacher/count")
	public ResponseEntity<ResponseWrapper<Long>> getTeachersCount() {
		return ResponseEntity.ok(new ResponseWrapper<>(userService.countAllByUserType(UserType.TEACHER)));
	}

	@GetMapping("/course/byType")
	public ResponseEntity<ResponseWrapper<Long>> getCoursesCountByType(@RequestParam String type) {
		return ResponseEntity.ok(new ResponseWrapper<>(courseService.countAllByType(CourseType.valueOf(type.toUpperCase()))));
	}

	@GetMapping("/student/byCourse")
	public ResponseEntity<ResponseWrapper<List<User>>> getStudentsByCourseType(@RequestParam Long courseId){
		return ResponseEntity.ok(new ResponseWrapper<>(userService.findAllByCourseIdAndUserType(courseId,UserType.STUDENT)));
	}

	@GetMapping("/student/byGroup")
	public ResponseEntity<ResponseWrapper<List<User>>> getStudentsByGroup(@RequestParam Long groupId){
		return ResponseEntity.ok(new ResponseWrapper<>(userService.findAllByGroupIdAndUserType(groupId,UserType.STUDENT)));
	}

	@GetMapping("/user/byGroupAndCourse")
	public ResponseEntity<ResponseWrapper<List<User>>> getUsersByGroupAndCourse(@RequestParam Long groupId, @RequestParam Long courseId){
		return ResponseEntity.ok(new ResponseWrapper<>(userService.findAllByGroupIdAndCourseId(groupId,courseId)));
	}

	@GetMapping("/student/byAgeAndCourse")
	public ResponseEntity<ResponseWrapper<List<User>>> getStudentsByGroup(@RequestParam Short age, @RequestParam Long courseId){
		return ResponseEntity.ok(new ResponseWrapper<>(userService.findAllOlderThanAndCourseIdAndUserType(age,courseId,UserType.STUDENT)));
	}


}

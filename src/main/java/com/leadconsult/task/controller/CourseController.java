package com.leadconsult.task.controller;

import com.leadconsult.task.helper.ResponseWrapper;
import com.leadconsult.task.model.Course;
import com.leadconsult.task.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/course")
public class CourseController {
	private final CourseService courseService;


	public CourseController(final CourseService courseService) {
		this.courseService = courseService;
	}

	@PostMapping
	public ResponseEntity<ResponseWrapper<Course>> saveCourse(@RequestBody Course course) {
		return ResponseEntity.ok(new ResponseWrapper<>(courseService.save(course)));
	}
}

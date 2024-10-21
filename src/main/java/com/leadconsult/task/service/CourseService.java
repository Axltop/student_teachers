package com.leadconsult.task.service;

import com.leadconsult.task.constant.CourseType;
import com.leadconsult.task.model.Course;
import com.leadconsult.task.repository.CourseRepository;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

	public final CourseRepository courseRepository;

	public CourseService(final CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}

	public Long countAllByType(CourseType courseType) {
		return courseRepository.countAllByType(courseType);
	}

	public Course save(Course course) {
		return courseRepository.save(course);
	}
}

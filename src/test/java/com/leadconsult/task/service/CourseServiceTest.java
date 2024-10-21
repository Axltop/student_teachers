package com.leadconsult.task.service;


import com.leadconsult.task.constant.CourseType;
import com.leadconsult.task.model.Course;
import com.leadconsult.task.repository.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {

	@Mock
	public CourseRepository courseRepository;

	@InjectMocks
	private CourseService courseService;

	private Course course;

	@BeforeEach
	public void setUp() {
		course = new Course();
		course.setCourseId(1L);
		course.setName("Test Course");
	}

	@Test
	public void testCountAllByType() {
		CourseType courseType = CourseType.MAIN;
		when(courseRepository.countAllByType(courseType)).thenReturn(5L);

		Long count = courseService.countAllByType(courseType);

		verify(courseRepository, times(1)).countAllByType(courseType);
	}

	@Test
	public void testSave() {
		when(courseRepository.save(any(Course.class))).thenReturn(course);

		Course savedCourse = courseService.save(course);

		verify(courseRepository, times(1)).save(course);
	}
}

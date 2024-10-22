package com.leadconsult.task.controller;


import com.leadconsult.task.TaskApplication;
import com.leadconsult.task.constant.CourseType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = TaskApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ReportControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testGetStudentsCount() throws Exception {

		mockMvc.perform(get("/report/student/count")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data").value(51));
	}

	@Test
	public void testGetTeachersCount() throws Exception {

		mockMvc.perform(get("/report/teacher/count")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data").value(49));
	}

	@Test
	public void testGetMainCoursesCount() throws Exception {

		mockMvc.perform(get("/report/course/byType")
						.param("type", CourseType.MAIN.getType())
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void testGetSecondaryCoursesCount() throws Exception {

		mockMvc.perform(get("/report/course/byType")
						.param("type", CourseType.SECONDARY.getType())
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data").value(7));


	}

	@Test
	public void testGetStudentsByCourseOne() throws Exception {

		mockMvc.perform(get("/report/student/byCourse")
						.param("courseId", "1")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data.length()").value(26L));
	}

	@Test
	public void testGetStudentsByCourseTwo() throws Exception {

		mockMvc.perform(get("/report/student/byCourse")
						.param("courseId", "2")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data.length()").value(27L));
	}

	@Test
	public void testGetStudentsByCourseThree() throws Exception {

		mockMvc.perform(get("/report/student/byCourse")
						.param("courseId", "3")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data.length()").value(22L));
	}

	@Test
	public void testGetStudentsByGroup() throws Exception {

		mockMvc.perform(get("/report/student/byGroup")
						.param("groupId", "1")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data.length()").value(40L));
	}

	@Test
	public void testGetUsersByGroupAndCourse() throws Exception {
		mockMvc.perform(get("/report/user/byGroupAndCourse")
						.param("groupId", "1")
						.param("courseId", "1")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data.length()").value(27));
	}

	@Test
	public void testGetStudentsByAgeAndCourse() throws Exception {

		mockMvc.perform(get("/report/student/byAgeAndCourse")
						.param("age", "18")
						.param("courseId", "1")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data.length()").value(12L));
	}
}

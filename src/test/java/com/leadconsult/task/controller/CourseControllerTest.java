package com.leadconsult.task.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leadconsult.task.TaskApplication;
import com.leadconsult.task.constant.CourseType;
import com.leadconsult.task.model.Course;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes= TaskApplication.class)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
public class CourseControllerTest {

	@Autowired
	private MockMvc mockMvc;


	private Course course;

	@BeforeEach
	public void setUp() {
		course = new Course();
		course.setName("Test Course");
		course.setType(CourseType.MAIN);
	}

	@Test
	@Order(1)
	public void testSaveCourse() throws Exception {
		mockMvc.perform(post("/course")
						.contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(course)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data.courseId").value(13))
				.andExpect(jsonPath("$.data.name").value("Test Course"));
	}

	@Test
	@Order(2)
	public void testSaveCourseNotUnique() throws Exception {
		mockMvc.perform(post("/course")
						.contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(course)))
				.andExpect(status().is4xxClientError());
	}
}

package com.leadconsult.task.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leadconsult.task.TaskApplication;
import com.leadconsult.task.model.Group;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes= TaskApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class GroupControllerTest {

	@Autowired
	private MockMvc mockMvc;

	private Group group;

	@BeforeEach
	public void setUp() {
		group = new Group();
		group.setName("Test Group");
	}

	@Test
	public void testSaveGroup() throws Exception {

		mockMvc.perform(post("/group")
						.contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(group)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data.groupId").value(6L))
				.andExpect(jsonPath("$.data.name").value("Test Group"));
	}
}


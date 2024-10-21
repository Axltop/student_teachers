package com.leadconsult.task.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leadconsult.task.model.Group;
import com.leadconsult.task.service.GroupService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GroupControllerTest {

	@Autowired
	private MockMvc mockMvc;

	private Group group;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		group = new Group();
		group.setGroupId(1L);
		group.setName("Test Group");
	}

	@Test
	public void testSaveGroup() throws Exception {
		mockMvc.perform(post("/group")
						.contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(group)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data.groupId").value(1L))
				.andExpect(jsonPath("$.data.name").value("Test Group"));
	}
}


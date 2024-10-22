package com.leadconsult.task.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leadconsult.task.TaskApplication;
import com.leadconsult.task.constant.UserType;
import com.leadconsult.task.model.User;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = TaskApplication.class)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	private User user =new User();

	@Test
	@Order(1)
	public void testSaveUser() throws Exception {
		user.setName("Jane Doe");
		user.setAge((short) 20);
		user.setUserId(110L);
		user.setUserType(UserType.STUDENT);

		MvcResult result = mockMvc.perform(post("/user")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(user)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data.name").value(user.getName()))
				.andReturn();

		JsonNode jsonNode = objectMapper.readTree(result.getResponse().getContentAsString());
		user.setUserId(jsonNode.get("data").get("userId").asLong());

	}

	@Test
	@Order(2)
	public void testUpdateUser() throws Exception {
		User updatedUser = new User();
		updatedUser.setUserId(2L);
		updatedUser.setName("new Name");
		updatedUser.setAge((short) 20);
		mockMvc.perform(put("/user")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(updatedUser)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data.userId").value(updatedUser.getUserId()))
				.andExpect(jsonPath("$.data.name").value("new Name"));
	}

	@Test
	@Order(3)
	public void testGetUser() throws Exception {
		mockMvc.perform(get("/user/{id}",3L)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data.userId").value(3L))
				.andExpect(jsonPath("$.data.name").value("Elsa Leffler"));
	}

	@Test
	@Order(4)
	public void testDeleteUser() throws Exception {
		mockMvc.perform(delete("/user/{id}", 3L)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.message").value("Success!"));
	}

	@Test
	@Order(4)
	public void testDeleteUserNotFound() throws Exception {
		mockMvc.perform(delete("/user/{id}", 999L)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.message").value("User with ID:999 does not exists"));
	}

	@Test
	public void testUpdateUser_UserNotFound() throws Exception {
		User nonExistentUser = new User();
		nonExistentUser.setUserId(999L);
		nonExistentUser.setName("Non Existent");

		mockMvc.perform(put("/user")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(nonExistentUser)))
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.message").value("User with ID:999 does not exists"));
	}
}

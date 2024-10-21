package com.leadconsult.task.controller;

import com.leadconsult.task.helper.ResponseWrapper;
import com.leadconsult.task.model.Group;
import com.leadconsult.task.service.GroupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/group")
public class GroupController {

	private final GroupService groupService;

	public GroupController(final GroupService groupService) {
		this.groupService = groupService;
	}

	@PostMapping
	public ResponseEntity<ResponseWrapper<Group>> saveGroup(@RequestBody Group group) {
		return ResponseEntity.ok(new ResponseWrapper<>(groupService.save(group)));
	}
}

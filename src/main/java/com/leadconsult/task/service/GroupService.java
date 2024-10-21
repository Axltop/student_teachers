package com.leadconsult.task.service;

import com.leadconsult.task.model.Group;
import com.leadconsult.task.repository.GroupRepository;
import org.springframework.stereotype.Service;

@Service
public class GroupService {
	private final GroupRepository groupRepository;

	public GroupService(final GroupRepository groupRepository) {
		this.groupRepository = groupRepository;
	}

	public Group save(Group group) {
		return groupRepository.save(group);
	}
}

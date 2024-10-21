package com.leadconsult.task.service;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.leadconsult.task.model.Group;
import com.leadconsult.task.repository.GroupRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GroupServiceTest {

	@Mock
	private GroupRepository groupRepository;

	@InjectMocks
	private GroupService groupService;

	private Group group;

	@BeforeEach
	public void setUp() {
		group = new Group();
		group.setGroupId(1L);
		group.setName("Test Group");
	}

	@Test
	public void testSave() {
		when(groupRepository.save(any(Group.class))).thenReturn(group);

		Group savedGroup = groupService.save(group);

		verify(groupRepository, times(1)).save(group);
	}
}

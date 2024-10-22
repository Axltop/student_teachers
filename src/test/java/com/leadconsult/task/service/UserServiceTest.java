package com.leadconsult.task.service;

import com.leadconsult.task.constant.UserType;
import com.leadconsult.task.exception.UserNotFound;
import com.leadconsult.task.model.User;
import com.leadconsult.task.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	@Mock
	private UserRepository userRepository;
	@InjectMocks
	private UserService userService;

	private final Long groupId = 1L;
	private final Long courseId = 1L;
	private User user;

	@BeforeEach
	public void setUp() {
		user = new User();
		user.setUserId(15L);
	}

	@Test
	public void testFindAllByGroupIdAndCourseId() {
		User user1 = new User();
		User user2 = new User();

		when(userRepository.findAllByGroupsGroupIdAndCoursesCourseId(groupId, courseId))
				.thenReturn(Arrays.asList(user1, user2));

		List<User> users = userService.findAllByGroupIdAndCourseId(groupId, courseId);

		assertThat(users).hasSize(2);
		assertThat(users).containsExactlyInAnyOrder(user1, user2);
		verify(userRepository,times(1)).findAllByGroupsGroupIdAndCoursesCourseId(groupId, courseId);
	}

	@Test
	public void testSave() {
		userService.save(user);

		verify(userRepository, times(1)).save(user);
	}

	@Test
	public void testUpdateStudentFound() throws UserNotFound {
		when(userRepository.findById(user.getUserId())).thenReturn(Optional.of(user));

		userService.update(user);
		verify(userRepository, times(1)).findById(user.getUserId());
		verify(userRepository, times(1)).save(user);
	}

	@Test
	public void testUpdateStudentNotFound() {
		when(userRepository.findById(user.getUserId())).thenReturn(Optional.empty());

		assertThatThrownBy(() -> userService.update(user))
				.isInstanceOf(UserNotFound.class)
				.hasMessage("User with ID:" + user.getUserId() + " does not exists");
	}

	@Test
	public void testGetByIdFound() throws UserNotFound {
		when(userRepository.findById(user.getUserId())).thenReturn(Optional.of(user));

		userService.getById(user.getUserId());

		verify(userRepository, times(1)).findById(user.getUserId());
	}

	@Test
	public void testGetByIdNotFound() {
		when(userRepository.findById(user.getUserId())).thenReturn(Optional.empty());

		assertThatThrownBy(() -> userService.getById(user.getUserId()))
				.isInstanceOf(UserNotFound.class);
	}

	@Test
	public void testDeleteById() throws UserNotFound {
		when(userRepository.findById(user.getUserId())).thenReturn(Optional.of(user));

		userService.deleteById(user.getUserId());

		verify(userRepository, times(1)).deleteById(user.getUserId());
	}

	@Test
	public void testCountAll() {
		when(userRepository.countAllByUserType(UserType.STUDENT)).thenReturn(51L);

		userService.countAllByUserType(UserType.STUDENT);

		verify(userRepository, times(1)).countAllByUserType(UserType.STUDENT);
	}

	@Test
	public void testFindAllByCourseId() {
		List<User> students = List.of(user);
		Long courseId = 1L;
		when(userRepository.findAllByCoursesCourseIdAndUserType(courseId,UserType.STUDENT)).thenReturn(students);

		userService.findAllByCourseIdAndUserType(courseId,UserType.STUDENT);

		verify(userRepository, times(1)).findAllByCoursesCourseIdAndUserType(courseId,UserType.STUDENT);
	}

	@Test
	public void testFindAllByGroupId() {
		List<User> students = List.of(user);
		Long groupId = 1L;
		when(userRepository.findAllByGroupsGroupIdAndUserType(groupId,UserType.STUDENT)).thenReturn(students);

		userService.findAllByGroupIdAndUserType(groupId,UserType.STUDENT);

		verify(userRepository, times(1)).findAllByGroupsGroupIdAndUserType(groupId,UserType.STUDENT);
	}

	@Test
	public void testFindAllOlderThanAndCourseId() {
		List<User> students = List.of(user);
		Short age = 18;
		Long courseId = 1L;
		when(userRepository.findAllByAgeGreaterThanEqualAndCoursesCourseIdIsAndUserType(age, courseId,UserType.STUDENT)).thenReturn(students);

		userService.findAllOlderThanAndCourseIdAndUserType(age, courseId,UserType.STUDENT);

		verify(userRepository, times(1)).findAllByAgeGreaterThanEqualAndCoursesCourseIdIsAndUserType(age, courseId,UserType.STUDENT);
	}
}
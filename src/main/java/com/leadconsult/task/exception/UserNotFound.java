package com.leadconsult.task.exception;

public class UserNotFound extends Throwable {
	public UserNotFound(Long userId) {
		super("User with ID:" + userId + " does not exists");
	}
}

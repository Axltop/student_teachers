package com.leadconsult.task.constant;

public enum UserType {
	TEACHER("teacher"),
	STUDENT("student");

	private final String type;

	UserType(String type) {
		this.type = type;
	}

	public String getType() {return type;}
}

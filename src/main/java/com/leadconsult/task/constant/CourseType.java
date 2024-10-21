package com.leadconsult.task.constant;

public enum CourseType {
	MAIN("main"),
	SECONDARY("secondary");

	private final String type;

	CourseType(String type) {
		this.type = type;
	}

	public String getType() {return type;}
}

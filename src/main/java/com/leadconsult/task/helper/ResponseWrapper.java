package com.leadconsult.task.helper;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseWrapper<T> {

	private T data;
	private Boolean success;
	private String message;
	private long timestamp = System.currentTimeMillis();


	public ResponseWrapper(T data ) {
		this.data = data;
		this.success = true;
	}

	public ResponseWrapper(Boolean success, String message) {
		this.success = success;
		this.message = message;
	}
}

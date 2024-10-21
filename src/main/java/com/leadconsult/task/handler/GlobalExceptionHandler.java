package com.leadconsult.task.handler;

import com.leadconsult.task.ResponseWrapper;
import com.leadconsult.task.exception.UserNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler  {
	@ExceptionHandler(UserNotFound.class)
	public ResponseEntity<ResponseWrapper<Object>> handleStudentNotFound(UserNotFound ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseWrapper<>(false,ex.getMessage()));
	}
}

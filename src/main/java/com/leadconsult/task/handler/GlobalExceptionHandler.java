package com.leadconsult.task.handler;

import com.leadconsult.task.exception.UserNotFound;
import com.leadconsult.task.helper.ResponseWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler  {
	@ExceptionHandler(UserNotFound.class)
	public ResponseEntity<ResponseWrapper<Object>> handleStudentNotFound(UserNotFound ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseWrapper<>(false,ex.getMessage()));
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ResponseWrapper<Object>> handleDataIntegrityViolationException(DataIntegrityViolationException exception) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new ResponseWrapper<>(false, "Ooops, there something wrong with request data." + Instant.now().toString()));

	}
}

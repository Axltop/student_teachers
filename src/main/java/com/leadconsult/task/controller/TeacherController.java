package com.leadconsult.task.controller;

import com.leadconsult.task.ResponseWrapper;
import com.leadconsult.task.exception.UserNotFound;
import com.leadconsult.task.model.Teacher;
import com.leadconsult.task.service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
	private final TeacherService teacherService;

	public TeacherController(final TeacherService teacherService) {
		this.teacherService = teacherService;
	}

	@PostMapping
	public ResponseEntity<ResponseWrapper<Teacher>> saveStudent(@RequestBody Teacher teacher) {
		return ResponseEntity.ok(new ResponseWrapper<>(teacherService.save(teacher)));
	}

	@PutMapping
	public ResponseEntity<ResponseWrapper<Teacher>> updateStudent(@RequestBody Teacher teacher) throws UserNotFound {
		return ResponseEntity.ok(new ResponseWrapper<>(teacherService.update(teacher)));
	}

	@GetMapping("/{id}")
	public  ResponseEntity<ResponseWrapper<Teacher>> getStudent(@PathVariable Long id ) {
		return ResponseEntity.ok(new ResponseWrapper<>(teacherService.getById(id)));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseWrapper<Void>> deleteStudent(@PathVariable Long id) {
		teacherService.deleteById(id);
		return ResponseEntity.ok(new ResponseWrapper<>(true,"Success!"));
	}
}

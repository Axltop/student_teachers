package com.leadconsult.task.controller;


import com.leadconsult.task.ResponseWrapper;
import com.leadconsult.task.exception.UserNotFound;
import com.leadconsult.task.model.Student;
import com.leadconsult.task.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

	private final StudentService studentService;

	public StudentController(final StudentService studentService) {
		this.studentService = studentService;
	}

	@PostMapping
	public ResponseEntity<ResponseWrapper<Student>> saveStudent(@RequestBody Student student) {
		return ResponseEntity.ok(new ResponseWrapper<>(studentService.save(student)));
	}

	@PutMapping
	public ResponseEntity<ResponseWrapper<Student>> updateStudent(@RequestBody Student student) throws UserNotFound {
		return ResponseEntity.ok(new ResponseWrapper<>(studentService.update(student)));
	}

	@GetMapping("/{id}")
	public  ResponseEntity<ResponseWrapper<Student>> getStudent(@PathVariable Long id ) {
		return ResponseEntity.ok(new ResponseWrapper<>(studentService.getById(id)));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseWrapper<Void>> deleteStudent(@PathVariable Long id) {
		studentService.deleteById(id);
		return ResponseEntity.ok(new ResponseWrapper<>(true,"Success!"));
	}
}

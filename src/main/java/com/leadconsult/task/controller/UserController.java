package com.leadconsult.task.controller;


import com.leadconsult.task.exception.UserNotFound;
import com.leadconsult.task.helper.ResponseWrapper;
import com.leadconsult.task.model.User;
import com.leadconsult.task.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

	private final UserService userService;

	public UserController(final UserService userService) {
		this.userService = userService;
	}

	@PostMapping
	public ResponseEntity<ResponseWrapper<User>> saveUser(@RequestBody User user) {
		return ResponseEntity.ok(new ResponseWrapper<>(userService.save(user)));
	}

	@PutMapping
	public ResponseEntity<ResponseWrapper<User>> update(@RequestBody User user) throws UserNotFound {
		return ResponseEntity.ok(new ResponseWrapper<>(userService.update(user)));
	}

	@GetMapping("/{id}")
	public  ResponseEntity<ResponseWrapper<User>> getUser(@PathVariable Long id ) throws UserNotFound {
		return ResponseEntity.ok(new ResponseWrapper<>(userService.getById(id)));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseWrapper<Void>> deleteUser(@PathVariable Long id) throws UserNotFound {
		userService.deleteById(id);
		return ResponseEntity.ok(new ResponseWrapper<>(true,"Success!"));
	}

}

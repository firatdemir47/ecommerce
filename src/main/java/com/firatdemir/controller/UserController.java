package com.firatdemir.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.firatdemir.dto.UserDto;
import com.firatdemir.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto, @RequestParam String password) {
		return ResponseEntity.ok(userService.createUser(userDto, password));
	}
}

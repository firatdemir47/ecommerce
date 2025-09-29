package com.firatdemir.service;

import java.util.List;

import com.firatdemir.dto.UserDto;

public interface UserService {

	UserDto createUser(UserDto userDto, String password);

	UserDto getUserById(Long id);

	List<UserDto> getAllUsers();

	UserDto updateUser(Long id, UserDto userDto);

	void deleteUser(Long id);

}

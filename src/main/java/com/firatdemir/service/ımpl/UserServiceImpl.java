package com.firatdemir.service.ımpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.firatdemir.dto.UserDto;
import com.firatdemir.model.User;
import com.firatdemir.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Override
	public UserDto createUser(UserDto userDto, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDto getUserById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDto> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDto updateUser(Long id, UserDto userDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(Long id) {
		// TODO Auto-generated method stub

	}

	private UserDto toDto(User entity) {
		UserDto dto = new UserDto();
		dto.setId(entity.getId());
		dto.setUsername(entity.getUsername());
		dto.setEmail(entity.getEmail());
		dto.setRole(entity.getRole());

		return dto;
	}

}

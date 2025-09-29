package com.firatdemir.service.ımpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.firatdemir.dto.UserDto;
import com.firatdemir.model.User;
import com.firatdemir.repository.UserRepository;
import com.firatdemir.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDto createUser(UserDto userDto, String password) {
		if (userRepository.existsByUsername(userDto.getUsername())) {
			throw new RuntimeException("Kullanıcı adı zaten mevcut: " + userDto.getUsername());
		}

		if (userRepository.existsByEmail(userDto.getEmail())) {
			throw new RuntimeException("Email zaten mevcut: " + userDto.getEmail());
		}
		User user = toEntity(userDto);
		user.setPassword(password);
		User saved = userRepository.save(user);
		return toDto(saved);

	}

	@Override
	public UserDto getUserById(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("kullanıcı bulunamadı,id: " + id));
		return toDto(user);
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

	private User toEntity(UserDto dto) {
		User user = new User();
		user.setId(dto.getId());
		user.setUsername(dto.getUsername());
		user.setEmail(dto.getEmail());
		user.setRole(dto.getRole());
		return user;
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

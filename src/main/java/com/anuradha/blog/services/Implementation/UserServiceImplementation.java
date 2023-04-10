package com.anuradha.blog.services.Implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anuradha.blog.entity.User;
import com.anuradha.blog.exceptions.ResourceNotFoundException;
import com.anuradha.blog.payloads.UserDto;
import com.anuradha.blog.repositories.UserRepo;
import com.anuradha.blog.services.UserService;

@Service
public class UserServiceImplementation implements UserService {
	@Autowired
	private UserRepo userRepo;
	@Override
	public UserDto createUser(UserDto userDto) {
		User user=dtoToUser(userDto);
		User savedUser=userRepo.save(user);
		return userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, int userId) {
		User user=userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", " Id ", userId));
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		User updatedUser=userRepo.save(user);
		return userToDto(updatedUser);
	}

	@Override
	public UserDto getUserById(int userId) {
		User user=userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", " Id ", userId));
		return userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users=userRepo.findAll();
		List<UserDto> userDtos=users.stream().map(user->userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(int userId) {
		User user=userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", " Id ", userId));
		userRepo.delete(user);
	}
	private User dtoToUser(UserDto userDto) {
		User user=new User();
		user.setId(userDto.getId());
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		return user;
	}
	
	private UserDto userToDto(User user) {
		UserDto userDto=new UserDto();
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setEmail(user.getEmail());
		userDto.setPassword(user.getPassword());
		userDto.setAbout(user.getAbout());
		return userDto;
	}
}

package com.anuradha.blog.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anuradha.blog.payloads.UserDto;
import com.anuradha.blog.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	UserService userService;
	
	//POST - create user
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
		UserDto createdUserDto=userService.createUser(userDto);
		return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);
	}
	
	//Put - update user
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable int userId){
		UserDto updatedUser=userService.updateUser(userDto, userId);
		return ResponseEntity.ok(updatedUser);
	}
	//GET - get user
	//Single user
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUser(@PathVariable int userId){
		UserDto user=userService.getUserById(userId);
		return ResponseEntity.ok(user);
	}
	//List of users
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		List<UserDto> users=userService.getAllUsers();
		return ResponseEntity.ok(users);
	}
	//DELETE - delete user
	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable("userId") int uid){
		userService.deleteUser(uid);
		return new ResponseEntity<>(Map.of("message", "user deleted succesfully"), HttpStatus.OK);
	}
}

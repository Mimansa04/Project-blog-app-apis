package com.mimansa.project.blog.services;

import java.util.List;

import com.mimansa.project.blog.payloads.UserDto;


public interface UserService {
	
	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user, Integer userId);
	UserDto getUserById(Integer userId);
	List<UserDto> getAllUsers();
	void deleteUser(Integer userId);

}

package com.mimansa.project.blog.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mimansa.project.blog.payloads.ApiResponse;
import com.mimansa.project.blog.payloads.UserDto;
import com.mimansa.project.blog.services.UserService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
	
	@NonNull
	private UserService userService;
	
	
	//add a new user
	@PostMapping("/create")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
		UserDto createdUserDto = this.userService.createUser(userDto);
		return new ResponseEntity<>(createdUserDto,HttpStatus.CREATED);
	}
	
	//update a user against a user id
	@PutMapping("/update/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("userId") Integer uId){
		UserDto updateduser = this.userService.updateUser(userDto, uId);
		return ResponseEntity.ok(updateduser);
		
	}
	
//	@DeleteMapping("delete/{userId}")
//	public ResponseEntity<?> deleteUser(@PathVariable("userId") Integer uId){
//		this.userService.deleteUser(uId);
//		return new ResponseEntity(Map.of("message", "user deleted successfully"), HttpStatus.OK);
//	}
	
	
	//Delete a user
	@DeleteMapping("delete/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer uId){
		this.userService.deleteUser(uId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("user deleted successfully",true), HttpStatus.OK);
	}
	
	
	//List of all users
	@GetMapping("/allUsers")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		return ResponseEntity.ok(this.userService.getAllUsers());
	}
	
	//Return a particular user
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable("userId") Integer uId){
		return ResponseEntity.ok(this.userService.getUserById(uId));
	}
	

}

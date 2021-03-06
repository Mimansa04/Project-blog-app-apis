package com.mimansa.project.blog.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter

public class UserDto {

	private int id;
	
	@NotBlank
	@Size(min=4, message="user name must be minimun of 4 characters")
	private String name;
	
	@Email(message= "email address is invalid")
	private String email;
	
	@NotBlank
	@Size(min=3, max=10, message="Password limit 3-10 chars ")
	//@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$")
	private String password;
	
	
	private String about;
	
	
	
}

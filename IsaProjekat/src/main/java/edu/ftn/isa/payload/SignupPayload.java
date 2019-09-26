package edu.ftn.isa.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

public @Data class SignupPayload {

	@NotNull
	@Size(min = 1)
	private String name;
	
	@NotNull
	@Size(min = 1)
	private String lastname;
	
	@NotNull
	@Size(min = 1)
	private String username;
	
	@NotNull
	@Email
	private String email;
	
	@Min(8)
	private String password;
	
	@NotNull
	@Size(min = 2)
	private String city;
	
	private String phoneNumber;
	
}

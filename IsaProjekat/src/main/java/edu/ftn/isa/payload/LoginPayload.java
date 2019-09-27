package edu.ftn.isa.payload;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@SuppressWarnings("unused")
public @Data class LoginPayload {

	@NotNull
	@Size(min=1)
	private String username;
	
	@NotNull
	@Size(min=8)
	private String password;
	
}

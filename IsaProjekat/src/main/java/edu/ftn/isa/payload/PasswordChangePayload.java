package edu.ftn.isa.payload;

import javax.validation.constraints.Size;

import lombok.Data;

public @Data class PasswordChangePayload {

	@Size(min=8)
	private String oldPassword;
	
	@Size(min=8)
	private String newPassword;
	
}

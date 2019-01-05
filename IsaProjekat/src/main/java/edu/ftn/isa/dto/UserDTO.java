package edu.ftn.isa.dto;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

public @Data class UserDTO {

	private String name;
	
	private String lastname;

	private String username;
	
	private String city;
	
}

package edu.ftn.isa.dto;

import lombok.Data;

public @Data class DestinationDTO {

	private String name;
	
	public DestinationDTO() {}
	public DestinationDTO(String name) {
		this.name = name;
	}
	
}

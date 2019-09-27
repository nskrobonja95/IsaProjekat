package edu.ftn.isa.dto;

import lombok.Data;

public @Data class DestinationDTO {

	public DestinationDTO(String name2) {
		this.name = name2;
	}

	public DestinationDTO() {
		// TODO Auto-generated constructor stub
	}

	private String name;
	
	private Long id;
	
}

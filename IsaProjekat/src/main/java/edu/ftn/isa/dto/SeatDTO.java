package edu.ftn.isa.dto;

import lombok.Data;

public @Data class SeatDTO {

	private String flightClass;
	
	private boolean fastRes;
	
	private int colNum;
	
	private int rowNum;
	
}

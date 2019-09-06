package edu.ftn.isa.dto;

import edu.ftn.isa.model.Flight;
import lombok.Data;

public @Data class SeatDTO {
	
	private Long id;

	private String flightClass;
	
	private boolean fastRes;
	
	private int colNum;
	
	private int seatNumber;
	
	private int rowNum;
	
	private boolean available;
	
	private boolean reserved;
	
	private Flight flight;
	
}

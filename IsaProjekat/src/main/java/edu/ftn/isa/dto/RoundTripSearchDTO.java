package edu.ftn.isa.dto;

import edu.ftn.isa.model.FlightClass;
import lombok.Data;

public @Data class RoundTripSearchDTO {

	private String from;
	
	private String to;
	
	private String departDate;
	
	private String returnDate;
	
	private int numOfPpl;
	
	private FlightClass flightClass;
	
}

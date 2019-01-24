package edu.ftn.isa.dto;

import lombok.Data;

public @Data class FlightReservationDTO {
	
	private Long flightId;
	
	private Long seatNumber;
	
	private String name;
	
	private String lastname;
	
	private String flightClass;
	
}

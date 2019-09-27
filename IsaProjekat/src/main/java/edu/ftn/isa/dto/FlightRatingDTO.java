package edu.ftn.isa.dto;

import lombok.Data;

public @Data class FlightRatingDTO {
	
	private Long flightId;
	
	private Long flightReservationId;
	
	private int rate;
	
}

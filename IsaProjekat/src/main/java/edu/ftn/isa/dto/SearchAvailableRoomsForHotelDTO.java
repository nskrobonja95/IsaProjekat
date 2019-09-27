package edu.ftn.isa.dto;

import lombok.Data;

public @Data class SearchAvailableRoomsForHotelDTO {

	private Long hotel;
	
	private String checkIn;
	
	private String checkOut;
	
}

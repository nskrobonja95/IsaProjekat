package edu.ftn.isa.dto;

import java.util.List;

import lombok.Data;

public @Data class SearchHotelResponseDTO {

	private List<HotelDTO> hotels;
	
	private String checkIn;
	
	private String checkOut;
	
}

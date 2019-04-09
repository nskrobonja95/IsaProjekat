package edu.ftn.isa.dto;

import java.util.List;

import edu.ftn.isa.model.Hotel;
import lombok.Data;

public @Data class SearchHotelResponseDTO {

	private List<Hotel> hotels;
	
	private String checkIn;
	
	private String checkOut;
	
}

package edu.ftn.isa.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

public @Data class FlightReservationResponseDTO {

	private boolean succesfullyReserved;
	
	private List<Integer> ids;
	
	public FlightReservationResponseDTO() {
		ids = new ArrayList<Integer>();
	}
	
}

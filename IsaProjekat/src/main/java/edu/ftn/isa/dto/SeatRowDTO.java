package edu.ftn.isa.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

public @Data class SeatRowDTO {

	public SeatRowDTO() {
		this.seats = new ArrayList<SeatDTO>();
	}
	
	private List<SeatDTO> seats;
	
}

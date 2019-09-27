package edu.ftn.isa.dto;

import java.util.List;

import lombok.Data;

public @Data class InviteFriendFlightReservationDTO {

	private String name;
	
	private String lastname;
	
	private String passportNumber;
	
	private String username;
	
	private List<SeatDTO> seats;
	
}

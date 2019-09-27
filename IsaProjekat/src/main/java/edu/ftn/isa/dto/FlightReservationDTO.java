package edu.ftn.isa.dto;

import java.util.List;

import edu.ftn.isa.model.Flight;
import edu.ftn.isa.model.FlightReservation;
import edu.ftn.isa.model.FlightSeat;
import lombok.Data;

public @Data class FlightReservationDTO {
	
	private String name;
	
	private String lastname;
	
	private String passportNumber;
	
	private List<SeatDTO> seats;
	
	private Boolean baggageChecked;
	
}

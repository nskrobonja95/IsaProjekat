package edu.ftn.isa.dto;

import edu.ftn.isa.model.Flight;
import edu.ftn.isa.model.FlightReservation;
import lombok.Data;

public @Data class FlightReservationDTO {
	
	private Long flightId;
	
	private int seatNumber;
	
	private String name;
	
	private String lastname;
	
	private String flightClass;
	
	private Flight flight;
	
	public FlightReservationDTO transformFromModel(FlightReservation fr) {
		FlightReservationDTO retVal = new FlightReservationDTO();
//		retVal.setFlightClass(fr.getFlightClass().name());
		retVal.setLastname(fr.getLastname());
		retVal.setName(fr.getName());
		return retVal;
	}
	
}

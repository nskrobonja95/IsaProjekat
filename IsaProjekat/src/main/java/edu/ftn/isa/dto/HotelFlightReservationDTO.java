package edu.ftn.isa.dto;

import edu.ftn.isa.model.Flight;
import edu.ftn.isa.model.FlightClass;
import lombok.Data;

public @Data class HotelFlightReservationDTO {

	private Flight flight;
	
	private Long flightTicketId;
	
	private int seatNumber;
	
	private FlightClass flightClass;
	
	private Long hotelReservationId;
	
}

package edu.ftn.isa.dto;

import edu.ftn.isa.model.FlightReservation;
import edu.ftn.isa.model.HotelReservation;
import lombok.Data;

public @Data class ReservationsDTO {

	private FlightReservation flightRes;
	
	private HotelReservation hotelRes;
	
}

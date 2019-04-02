package edu.ftn.isa.dto;

import java.util.List;

import edu.ftn.isa.model.Flight;
import lombok.Data;

public @Data class RoundTripFlights {

	private List<Flight> directFlights;
	
	private List<Flight> returnFlights;
	
}

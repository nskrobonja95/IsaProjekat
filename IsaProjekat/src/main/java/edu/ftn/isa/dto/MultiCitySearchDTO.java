package edu.ftn.isa.dto;

import edu.ftn.isa.model.FlightClass;
import lombok.Data;

public @Data class MultiCitySearchDTO {

	private String from;
	
	private String midDest;
	
	private String to;
	
	private String departDate1;
	
	private String departDate2;
	
	private int numOfPpl;
	
	private FlightClass flightClass;
	
}

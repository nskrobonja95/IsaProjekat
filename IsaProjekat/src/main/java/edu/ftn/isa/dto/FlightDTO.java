package edu.ftn.isa.dto;

import java.util.Date;

import lombok.Data;

public @Data class FlightDTO {

	private String from;
	
	private String to;
	
	private String depart;
	
	private String land;
	
	private float priceForBaggageOver7kg;
	
	private float priceForBaggageOver14kg;
	
	private double economicPrice;
	
	private double businessPrice;
	
	private int numOfRows;
	
	private String configType;
	
}

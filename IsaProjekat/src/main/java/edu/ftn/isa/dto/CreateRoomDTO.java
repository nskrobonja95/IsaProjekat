package edu.ftn.isa.dto;

import java.util.List;

import lombok.Data;

public @Data class CreateRoomDTO {

	private String description;
	
	private int numOfBeds;
	
	private boolean balcony;
	
	private List<PriceOfMonthDTO> monthPrices;
	
	private List<String> services;
	
}

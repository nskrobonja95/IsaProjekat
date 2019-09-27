package edu.ftn.isa.dto;

import lombok.Data;

public @Data class PriceOfMonthDTO {
	
	private Long id;

	private double price;
	
	private String from;
	
	private String to;
	
}

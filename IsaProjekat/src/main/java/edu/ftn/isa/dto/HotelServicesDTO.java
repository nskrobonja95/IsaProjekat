package edu.ftn.isa.dto;

import javax.validation.constraints.NotNull;

import edu.ftn.isa.model.Hotel;
import lombok.Data;

public @Data class HotelServicesDTO {

	@NotNull
	private Hotel hotel;
	
	@NotNull
	private String name;
	
	private Double rate;
	
	private String charge;
	
}

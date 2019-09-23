package edu.ftn.isa.dto;

import java.util.List;
import lombok.Data;

public @Data class EditRoomDTO {
	
	private Long id;
	
	private int numOfBeds;
	
	private String description;
	
	private boolean balcony;
	
	private List<HotelServiceDTO> hotelServices;
	
	private List<PriceOfMonthDTO> prices;
	
}

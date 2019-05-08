package edu.ftn.isa.dto;

import java.util.List;

import lombok.Data;

public @Data class RoomPricesDTO {

	private List<PriceOfMonthDTO> monthPrices;
	
}

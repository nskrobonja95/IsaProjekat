package edu.ftn.isa.dto;

import java.util.List;

import lombok.Data;

public @Data class AvioStatisticsDTO {

	float avgAvioRate;
	
	List<FlightForStatsDTO> flights;
	
	private Long dailySoldTickets;
	
	private Long weeklySoldTickets;
	
	private Long monthlySoldTickets;
	
}

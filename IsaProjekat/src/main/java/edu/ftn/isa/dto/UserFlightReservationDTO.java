package edu.ftn.isa.dto;

import java.util.ArrayList;
import java.util.List;

import edu.ftn.isa.model.FlightSeat;
import lombok.Data;

public @Data class UserFlightReservationDTO {
	private Long reservationId;
	
	private List<SeatDTO> seats;
	
	private int flightRate;
	
	private String name;
	
	private String lastname;
	
	private Double price;

	private String status;
	public UserFlightReservationDTO(Long reservationId, List<FlightSeat> seats, int flightRate, String name,
			String lastname, String status, Double price) {
		super();
		this.reservationId = reservationId;
		this.seats = parseSeatDTOList(seats);
		this.flightRate = flightRate;
		this.name = name;
		this.lastname = lastname;
		this.status = status;
		this.price = price;
	}
	private List<SeatDTO> parseSeatDTOList(List<FlightSeat> seats){
		List<SeatDTO> seatsDTO = new ArrayList<SeatDTO>();
		
		for(FlightSeat seat : seats) {
			seatsDTO.add(SeatDTO.parseToSeatDTO(seat));
		}
		
		return seatsDTO;
		
	}
	
	
}

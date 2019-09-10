package edu.ftn.isa.dto;

import edu.ftn.isa.model.Flight;
import edu.ftn.isa.model.FlightSeat;
import lombok.Data;

public @Data class SeatDTO {
	
	private Long id;

	private String flightClass;
	
	private boolean fastRes;
	
	private int colNum;
	
	private int seatNumber;
	
	private int rowNum;
	
	private boolean available;
	
	private boolean reserved;
	
	private Flight flight;
	
	public static SeatDTO parseToSeatDTO(FlightSeat seat) {
		return new SeatDTO(seat.getId(), 
				seat.getFlightClass().toString(), 
				seat.getColNo(), 
				seat.getSeatNumber(), 
				seat.getRowNo(),
				seat.getFlight());
	}

	public SeatDTO(Long id, String flightClass, int colNum, int seatNumber, int rowNum,
			 Flight flight) {
		super();
		this.id = id;
		this.flightClass = flightClass;
		this.fastRes = fastRes;
		this.colNum = colNum;
		this.seatNumber = seatNumber;
		this.rowNum = rowNum;
		this.available = available;
		this.reserved = reserved;
		this.flight = flight;
	}

	public SeatDTO() {
		super();
	}
}

package edu.ftn.isa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Embeddable
public @Data class FlightReservationIdentity implements Serializable {

	@Column(name="flightticketID")
	@NotNull
	private Long reservationId;
	
	@Column(name="flight")
	@NotNull
	private Long flightId;
	
	@Column(name="seatnumber")
	@NotNull
	private int seatNumber;
	
}

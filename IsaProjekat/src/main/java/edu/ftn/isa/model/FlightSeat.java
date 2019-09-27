package edu.ftn.isa.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import lombok.Data;

@Entity
@Table(name="flightSeat")
public @Data class FlightSeat {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="flightSeatId")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="flightId")
	private Flight flight;

	@Column(name="flightclass")
	private FlightClass flightClass;
	
	@Column(name="available")
	private boolean available;
	
	@Column(name="seatNumber")
	private int  seatNumber;
	
	@Column(name="rowNo")
	private int rowNo;
	
	@Column(name="colNo")
	private int colNo;
	
	@Column(name="fastReservation")
	private boolean fastReservation;
	
	@ManyToMany(mappedBy = "flightReservationSeats")
	private List<FlightReservation> reservations;
	
}

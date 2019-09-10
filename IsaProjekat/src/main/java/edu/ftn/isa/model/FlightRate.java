package edu.ftn.isa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="flightRate")
public @Data class FlightRate {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="flightRateId")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="flightId")
	private Flight flight;
	
	@ManyToOne
	@JoinColumn(name="flightReservationId")
	private FlightReservation flightReservation;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@Column(name="rate")
	private Integer rate;
	
}

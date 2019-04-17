package edu.ftn.isa.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Table(name="flightReservation")
public @Data class FlightReservation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="flightReservationId")
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="lastname")
	private String lastname;
	
	@JsonFormat(pattern="yyyy:MM:dd")
	@Column(name="reserveDate")
	private Date reserveDate;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@Column(name="status")
	private ReservationStatus status;
	
	@Column(name="fastReservation")
	private boolean fastReservation;
	
	@ManyToMany
	@JoinTable( name = "RESERVATION_SEAT",
    	joinColumns = @JoinColumn(name = "reservationId"),
    	inverseJoinColumns = @JoinColumn(name = "seatId") )
	private List<FlightSeat> flightReservationSeats;
	
}

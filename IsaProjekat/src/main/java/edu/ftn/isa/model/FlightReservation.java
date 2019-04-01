package edu.ftn.isa.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Table(name="flightReservation")
public @Data class FlightReservation {

	@EmbeddedId
	private FlightReservationIdentity id;
	
	@Column(name="flightclass")
	private FlightClass flightClass;
	
	@Column(name="name")
	private String name;
	
	@Column(name="lastname")
	private String lastname;
	
	@JsonFormat(pattern="yyyy:MM:dd")
	@Column(name="reserveDate")
	private Date reserveDate;
	
	@Column(name="isCanceled")
	private boolean isCanceled;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
}

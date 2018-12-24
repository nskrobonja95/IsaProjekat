package edu.ftn.isa.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;

public @Data class HotelService {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="hotelserviceID")
	private Long id;
	
	@Column(name="servicename")
	private String name;
	
	@Column(name="rate")
	private Double rate;
	
	@Column(name="charge")
	private String charge;
	
	@ManyToMany(mappedBy = "services")
	private Collection<HotelReservation> reservations;
	
}

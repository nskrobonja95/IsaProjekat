package edu.ftn.isa.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.Data;

public @Data class HotelReservation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="hotelreservationID")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="hotel_id")
	private Hotel hotel;
	
	@ManyToMany
	@JoinTable(
			name = "PERSISTING_RESERVATION_SERVICE", 
			joinColumns = @JoinColumn(name = "reservation_id", referencedColumnName = "hotelreservationID"),
			inverseJoinColumns = @JoinColumn(name = "service_id", referencedColumnName = "hotelserviceID"))
	private Collection<HotelService> services;
	
}

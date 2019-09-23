package edu.ftn.isa.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Entity
@Table(name="hotelService")
public @Data class HotelServiceModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="hotelserviceID")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="hotel_id")
	private Hotel hotel;
	
	@Column(name="servicename")
	private String name;
	
	@Column(name="rate")
	private Double rate;
	
	@Column(name="charge")
	private String charge;
	
	@JsonManagedReference
	@ManyToMany(mappedBy = "services")
	private Collection<HotelReservation> reservations;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "hotelServices")
	private Collection<Room> rooms;
	
}

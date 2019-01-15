package edu.ftn.isa.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Table(name="hotelReservation")
public @Data class HotelReservation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="hotelreservationID")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="hotel_id")
	private Hotel hotel;
	
	@ManyToOne
	@JoinColumn(name="room_id")
	private Room room;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	@Column(name="arrivaldate")
	private Date arrivalDate;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	@Column(name="departingdate")
	private Date departingDate;
	
	@ManyToMany
	@JoinTable(
			name = "PERSISTING_RESERVATION_SERVICE", 
			joinColumns = @JoinColumn(name = "reservation_id", referencedColumnName = "hotelreservationID"),
			inverseJoinColumns = @JoinColumn(name = "service_id", referencedColumnName = "hotelserviceID"))
	private Collection<HotelService> services;
	
	@Column(name="canceled")
	private boolean canceled;
	
}

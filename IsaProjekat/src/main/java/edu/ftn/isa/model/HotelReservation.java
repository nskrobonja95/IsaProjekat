package edu.ftn.isa.model;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.Version;
import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name="hotelReservation")
public @Data class HotelReservation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="hotelreservationID")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="room_id")
	private Room room;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@OneToOne
	@Nullable
	private FlightReservation flightResId;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	@Column(name="arrivaldate")
	private Date arrivalDate;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	@Column(name="departingdate")
	private Date departingDate;
	
	@JsonBackReference
	@ManyToMany
	@JoinTable(
			name = "PERSISTING_RESERVATION_SERVICE", 
			joinColumns = @JoinColumn(name = "reservation_id", referencedColumnName = "hotelreservationID"),
			inverseJoinColumns = @JoinColumn(name = "service_id", referencedColumnName = "hotelserviceID"))
	private Collection<HotelServiceModel> services;
	
	@Column(name="canceled")
	private boolean canceled;
	
	@Column(name="status")
	private ReservationStatus status;
	
	@Column(name="rating")
	private Integer rating;
	
	@Column(name="fastReservation")
	private boolean fastReservation;
	
	@Column(name="discount")
	private Double discount;
	
	@javax.persistence.Version
	@Column(name="version")
	private int version;

}

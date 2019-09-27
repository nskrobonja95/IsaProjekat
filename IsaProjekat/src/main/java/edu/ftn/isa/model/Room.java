package edu.ftn.isa.model;

import java.util.Collection;
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
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Entity
@Table(name="room")
public @Data class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="roomID")
	private Long id;
	
	@Column(name="numofbeds")
	private int numOfBeds;
	
	@Column(name="roomdescription")
	private String description;
	
	@ManyToOne
	@JoinColumn(name="hotelID")
	private Hotel hotel;
	
	@Column(name="balcony")
	private boolean balcony;
	
	@ManyToMany
	@JoinTable(
			name = "ROOM_SERVICE", 
			joinColumns = @JoinColumn(name = "room_id", referencedColumnName = "roomID"),
			inverseJoinColumns = @JoinColumn(name = "service_id", referencedColumnName = "hotelserviceID"))
	private Collection<HotelServiceModel> hotelServices;
	
	@OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
	private List<PriceOfRoom> prices;
	
}

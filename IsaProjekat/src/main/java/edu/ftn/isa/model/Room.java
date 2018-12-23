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
@Table(name="room")
public @Data class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="roomID")
	private Long id;
	
	@Column(name="airconditioning")
	private boolean airConditioning;
	
	@Column(name="numofbeds")
	private int numOfBeds;
	
	@Column(name="roomdescription")
	private String description;
	
	@ManyToOne
	@JoinColumn(name="hotelID")
	private Hotel hotel;
	
}

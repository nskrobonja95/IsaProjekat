package edu.ftn.isa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Table(name="vehicles")
public @Data class Vehicle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="vehicleID")
	private Long id;
	
	@NotNull
	@Column(name="registrationPlate")
	private String registrationPlate;
	
	@Column(name="model")
	private String model;
	
	@ManyToOne
	@JoinColumn(name="rentacarID")
	private RentACarService rentACarService;
	
}

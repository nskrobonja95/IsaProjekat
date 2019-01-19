package edu.ftn.isa.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name="rentACarService")
public @Data class RentACarService {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="rentacarID")
	private Long id;
	
	@Column(name="rentacarname")
	private String name;
	
	@NotNull
	@Column(name="rentacaraddress")
	private String address;
	
	@NotNull
	@Column(name="rentacarpromo")
	private String promo;
	
	@JsonIgnore
	@OneToMany(mappedBy="rentACarService")
	private Collection<Vehicle> vehicels;
	
}

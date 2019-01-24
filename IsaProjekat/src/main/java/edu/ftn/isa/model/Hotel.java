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
@Table(name="hotel")
public @Data class Hotel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="hotelID")
	private Long id;
	
	@Column(name="hotelname")
	private String name;
	
	@NotNull
	@Column(name="hoteladdress")
	private String address;
	
	@NotNull
	@Column(name="hotelpromo")
	private String promo;
	
	@ManyToOne
	@JoinColumn(name = "admin", nullable=true)
	private User admin;
	
}

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Entity
@Table(name="flights")
public @Data class Flight {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="flightID")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="aviocompany_id", nullable = false)
	private AvioCompany avioCompany;
	
	@ManyToOne
	@JoinColumn(name = "fromdest_id", nullable = false)
	private Destination from;
	
	@ManyToOne
	@JoinColumn(name = "todest_id", nullable = false)
	private Destination toDest;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd@HH:mm")
	@Temporal(TemporalType.DATE)
	@Column(name="takeoff")
	private Date takeoff;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd@HH:mm")
	@Temporal(TemporalType.DATE)
	@Column(name="landing")
	private Date landing;
	
	@Column(name="economicprice")
	private Double economicClassPrice;
	
	@Column(name="bussinessprice")
	private Double bussinessClassPrice;
	
	@Column(name="number_of_seats")
	private int numberOfSeats;
	
}

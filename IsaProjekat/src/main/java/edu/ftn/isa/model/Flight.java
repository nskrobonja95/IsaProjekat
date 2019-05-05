package edu.ftn.isa.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

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

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="takeoff")
	private Date takeoff;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="landing")
	private Date landing;
	
	@Column(name="economicprice")
	private Double economicClassPrice;
	
	@Column(name="bussinessprice")
	private Double bussinessClassPrice;
	
	@Column(name="numOfRows")
	private int numOfRows;
	
	@Column(name="configurationType")
	private String configurationType;
	
	@Column(name="baggageOver7Price")
	private float baggageOver7Price;
	
	@Column(name="baggageOver20Price")
	private float baggageOver20Price;
	
}

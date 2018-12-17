package edu.ftn.isa.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="destination")
public @Data class Destination implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="destID")
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="avioID")
	private Long avioID;
	
	@ManyToMany(mappedBy = "destinations")
	private Collection<AvioCompany> avioCompanies;
}

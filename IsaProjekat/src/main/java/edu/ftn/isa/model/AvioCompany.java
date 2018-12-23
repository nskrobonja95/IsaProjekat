package edu.ftn.isa.model;

import java.io.Serializable;
import java.util.Collection;

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
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Table(name="aviocompany")
public @Data class AvioCompany implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="avioID")
	private Long id;
	
	@NotNull
	@Column(name="name")
	private String name;
	
	@NotNull
	@Column(name="address")
	private String address;
	
	@Column(name="promo")
	private String promo;
	
	@ManyToMany
	@JoinTable(
			name = "PERSISTING_AVIO_DESTINATION", 
			joinColumns = @JoinColumn(name = "avio_id", referencedColumnName = "avioID"),
			inverseJoinColumns = @JoinColumn(name = "dest_id", referencedColumnName = "destID"))
	private Collection<Destination> destinations;
	
	@ManyToOne
	@JoinColumn(name = "admin", insertable = false, updatable = false)
	private User admin;
	
}

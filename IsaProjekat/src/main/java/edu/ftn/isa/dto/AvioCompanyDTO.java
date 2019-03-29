package edu.ftn.isa.dto;

import java.util.List;

import javax.persistence.Column;

import edu.ftn.isa.model.User;
import lombok.Data;

public @Data class AvioCompanyDTO {
	
	private Long id;
	
	private String name;
	
	private String address;
	
	@Column(name="promo")
	private String promo;
	
	private List<String> destinations;
	private User admin;
	
}

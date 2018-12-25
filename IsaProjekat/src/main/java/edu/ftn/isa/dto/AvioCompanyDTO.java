package edu.ftn.isa.dto;

import javax.persistence.Column;
import edu.ftn.isa.model.User;
import lombok.Data;

public @Data class AvioCompanyDTO {

	private String name;
	
	private String address;
	
	@Column(name="promo")
	private String promo;
	
	private User admin;
	
}

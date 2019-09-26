package edu.ftn.isa.dto;

import edu.ftn.isa.model.AvioCompany;
import lombok.Data;

public @Data class AddAvioResponseDTO {

	private AvioCompany	avio;
	
	private int status;
}

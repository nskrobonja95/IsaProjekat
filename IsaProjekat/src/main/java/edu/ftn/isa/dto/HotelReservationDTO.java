package edu.ftn.isa.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonFormat;

import edu.ftn.isa.model.Hotel;
import edu.ftn.isa.model.HotelServiceModel;
import edu.ftn.isa.model.Room;
import lombok.Data;

public @Data class HotelReservationDTO {

	private Long roomId;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private Date arrivalDate;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private Date departingDate;
	
	private List<String> hotelServices;
	
	private List<Integer> flightReservationIds;
	
}

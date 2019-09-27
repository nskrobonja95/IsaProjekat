package edu.ftn.isa.dto;

import java.util.Collection;
import java.util.Date;

import edu.ftn.isa.model.HotelServiceModel;
import edu.ftn.isa.model.Room;
import lombok.Data;

public @Data class UserHotelReservationDTO {
	private Long id;
	private Room room;
	private Date arrivalDate;
	private Date departingDate;
	private String status;
	private Integer rating;
	private Collection<HotelServiceModel> services;
	
	public UserHotelReservationDTO(Long id, Room room, Date arrivalDate, Date departingDate, String status,
			Integer rating, Collection<HotelServiceModel> services) {
		super();
		this.id = id;
		this.room = room;
		this.arrivalDate = arrivalDate;
		this.departingDate = departingDate;
		this.status = status;
		this.rating = rating;
		this.services = services;
	}

	
	
}

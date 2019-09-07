package edu.ftn.isa.dto;


import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import edu.ftn.isa.model.HotelService;
import edu.ftn.isa.model.PriceOfRoom;
import lombok.Data;

public @Data class AvailableRoomDTO {

private Long id;
	
	private int numOfBeds;
	
	private String description;
	
	private boolean balcony;
	
	private Collection<HotelService> hotelServices;
	
	private List<PriceOfRoom> prices;
	
}

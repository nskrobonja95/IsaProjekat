package edu.ftn.isa.dto;

import edu.ftn.isa.model.Hotel;
import lombok.Data;

public @Data class HotelDTO {

	private Long id;
	private String name;
	private String address;
	private String promo;
	private Long admin;
	
	public static HotelDTO parseHotel(Hotel hotel) {
		HotelDTO temp = new HotelDTO();
		temp.setId(hotel.getId());
		temp.setAddress(hotel.getAddress());
		temp.setName(hotel.getName());
		temp.setPromo(hotel.getPromo());
		//temp.setAdmin(hotel.getAdmin().getId());
		return temp;
	}
}

package edu.ftn.isa.dto;

import edu.ftn.isa.model.Hotel;
import edu.ftn.isa.model.RentACarService;
import lombok.Data;

public @Data class RentACarServiceDTO {
	private Long id;
	private String name;
	private String address;
	private String promo;
	
	public static RentACarServiceDTO parseRentACar(RentACarService rentACar) {
		RentACarServiceDTO temp = new RentACarServiceDTO();
		temp.setAddress(rentACar.getAddress());
		temp.setName(rentACar.getName());
		temp.setPromo(rentACar.getPromo());
		//temp.setAdmin(hotel.getAdmin().getId());
		return temp;
	}
}

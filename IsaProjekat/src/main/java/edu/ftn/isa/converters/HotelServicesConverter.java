package edu.ftn.isa.converters;

import org.springframework.stereotype.Component;

import edu.ftn.isa.dto.HotelServicesDTO;
import edu.ftn.isa.model.HotelServiceModel;

@Component
public class HotelServicesConverter {

	public HotelServicesDTO fromModelToDto(HotelServiceModel service) {
		HotelServicesDTO serviceDTO = new HotelServicesDTO();
		serviceDTO.setHotel(service.getHotel());
		serviceDTO.setName(service.getName());
		serviceDTO.setCharge(service.getCharge());
		serviceDTO.setRate(service.getRate());
		return serviceDTO;
	}

	
}

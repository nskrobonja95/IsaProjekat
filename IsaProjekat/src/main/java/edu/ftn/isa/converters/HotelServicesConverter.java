package edu.ftn.isa.converters;

import org.springframework.stereotype.Component;

import edu.ftn.isa.dto.HotelServicesDTO;
import edu.ftn.isa.model.HotelService;

@Component
public class HotelServicesConverter {

	public HotelServicesDTO fromModelToDto(HotelService service) {
		HotelServicesDTO serviceDTO = new HotelServicesDTO();
		serviceDTO.setRoom(service.getRoom());
		serviceDTO.setName(service.getName());
		serviceDTO.setCharge(service.getCharge());
		serviceDTO.setRate(service.getRate());
		return serviceDTO;
	}

	
}

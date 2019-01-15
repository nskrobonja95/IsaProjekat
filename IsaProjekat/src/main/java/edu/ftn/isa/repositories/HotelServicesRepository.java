package edu.ftn.isa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ftn.isa.model.Hotel;
import edu.ftn.isa.model.HotelService;

public interface HotelServicesRepository extends JpaRepository<HotelService, Long> {

	List<HotelService> findByName(String name);
	
	HotelService findByNameAndHotel(String name, Hotel hotel);
	
	List<HotelService> findByHotel(Hotel hotel);
	
}

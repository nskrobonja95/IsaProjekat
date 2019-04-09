package edu.ftn.isa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.ftn.isa.model.Hotel;
import edu.ftn.isa.model.HotelService;
import edu.ftn.isa.model.Room;

public interface HotelServicesRepository extends JpaRepository<HotelService, Long> {

	List<HotelService> findByName(String name);
	
	@Query("SELECT hs from HotelService hs where hs.room.hotel.id = :hotel"
			+ " and hs.name = :name")
	HotelService retrieveByNameAndHotel(@Param("name") String name, @Param("hotel") Long hotel);
	
	@Query("SELECT hs from HotelService hs where hs.room.hotel = :hotel")
	List<HotelService> findByHotel(@Param("hotel") Hotel hotel);
	
}

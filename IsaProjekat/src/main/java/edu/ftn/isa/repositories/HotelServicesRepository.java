package edu.ftn.isa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.ftn.isa.model.Hotel;
import edu.ftn.isa.model.HotelServiceModel;

public interface HotelServicesRepository extends JpaRepository<HotelServiceModel, Long> {

	List<HotelServiceModel> findByName(String name);
	
	@Query("SELECT hs from HotelServiceModel hs where hs.hotel.id = :hotel"
			+ " and hs.name = :name")
	HotelServiceModel retrieveByNameAndHotel(@Param("name") String name, @Param("hotel") Long hotel);
	
	List<HotelServiceModel> findByHotel(Hotel hotel);
	
}

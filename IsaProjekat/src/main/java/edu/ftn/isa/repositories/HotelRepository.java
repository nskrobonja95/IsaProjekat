package edu.ftn.isa.repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.ftn.isa.model.Destination;
import edu.ftn.isa.model.Hotel;
import edu.ftn.isa.model.User;

public interface HotelRepository extends JpaRepository<Hotel, Long>{

	Optional<Hotel> findById(Long id);
	
//	@Query("SELECT DISTINCT h FROM Hotel h LEFT JOIN Room r ON r.hotel = h "
//			+ "WHERE h.destination = :dest and "
//			+ "r NOT IN (SELECT r1 FROM Room r1 INNER JOIN HotelReservation hr "
//			+ "ON r1 = hr.room "
//			+ "WHERE (hr.arrivalDate BETWEEN :checkIn and :checkOut or "
//			+ " hr.departingDate BETWEEN :checkIn and :checkOut) "
//			+ "and hr.canceled = false)")
//	List<Hotel> searchAvailableHotels(@Param("checkIn") Date checkIn,
//			@Param("checkOut") Date checkOut, @Param("dest") Destination dest);

	@Query("SELECT DISTINCT r.hotel FROM Room r "
			+ "WHERE r.hotel.destination = :dest and "
			+ "r NOT IN (SELECT hr.room FROM HotelReservation hr "
			+ ""
			+ "WHERE (hr.arrivalDate BETWEEN :checkIn and :checkOut or "
			+ " hr.departingDate BETWEEN :checkIn and :checkOut or"
			+ " (hr.arrivalDate < :checkIn and hr.departingDate > :checkOut)) "
			+ "and hr.canceled = false)")
	List<Hotel> searchAvailableHotels(@Param("checkIn") Date checkIn,
			@Param("checkOut") Date checkOut, @Param("dest") Destination dest);
	
	Hotel findByAdmin(User user);
	
	@Query("SELECT h from Hotel h where h.admin = :user")
	List<Hotel> findHotelsByAdmin(@Param("user") User user);
	
	
}

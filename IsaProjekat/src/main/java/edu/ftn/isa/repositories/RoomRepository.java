package edu.ftn.isa.repositories;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.ftn.isa.model.Destination;
import edu.ftn.isa.model.Hotel;
import edu.ftn.isa.model.HotelReservation;
import edu.ftn.isa.model.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {

	List<Room> findByHotel(Hotel hotel);
	
	@Query("SELECT r FROM Room r LEFT JOIN Hotel h ON r.hotel = h "
			+ "WHERE h.destination = :dest and "
			+ "r NOT IN (SELECT r1 FROM Room r1 INNER JOIN HotelReservation hr "
			+ "ON r1 = hr.room "
			+ "WHERE (hr.arrivalDate BETWEEN :checkIn and :checkOut or "
			+ " hr.departingDate BETWEEN :checkIn and :checkOut) "
			+ "and hr.canceled = false)")
	List<Room> searchAvailableRooms(@Param("checkIn") Date checkIn,
			@Param("checkOut") Date checkOut, @Param("dest") Destination dest);

	@Query("SELECT r FROM Room r"
			+ " WHERE r.hotel.id = :hotelId and "
			+ "r NOT IN (SELECT hr.room FROM HotelReservation hr "
			+ "WHERE (hr.arrivalDate BETWEEN :checkIn and :checkOut or "
			+ " hr.departingDate BETWEEN :checkIn and :checkOut or" 
			+ " (hr.arrivalDate < :checkIn and hr.departingDate > :checkOut)) "
			+ "and hr.canceled = false)")
	List<Room> searchAvailableRoomsForHotel(@Param("checkIn") Date checkIn, @Param("checkOut") Date checkOut, @Param("hotelId") Long hotelId);
	
	@Modifying
	@Transactional
	void delete(Room r);

	@Query("SELECT AVG(hr.rating) FROM HotelReservation hr where hr.room = :room and hr.rating!=0")
	Float getAverageRatingForRoom(@Param("room") Room room);
	
}

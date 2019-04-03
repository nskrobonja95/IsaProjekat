package edu.ftn.isa.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.ftn.isa.model.Destination;
import edu.ftn.isa.model.Hotel;
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
	
}

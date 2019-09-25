package edu.ftn.isa.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.ftn.isa.model.Hotel;
import edu.ftn.isa.model.HotelReservation;
import edu.ftn.isa.model.ReservationStatus;
import edu.ftn.isa.model.Room;
import edu.ftn.isa.model.User;

public interface HotelReservationRepository extends JpaRepository<HotelReservation, Long>{

	@Query("SELECT hr FROM HotelReservation hr where "
			+ "hr.user = :user and hr.flightResId is not null")
	List<HotelReservation> findByUserAndBoundedToFlightRes(@Param("user") User user);
	
	@Query("SELECT hr FROM HotelReservation hr where "
			+ "hr.user = :user and hr.flightResId = NULL")
	List<HotelReservation> findSoleReservationsByUser(@Param("user") User user);
	
	@Query("select hr from HotelReservation hr where hr.room = :room and"
			+ " hr.departingDate > CURDATE()")
	List<HotelReservation> checkIfHotelRoomReserved(@Param("room") Room room);
	
	List<HotelReservation> findByUser(User user);
	
	@Query("select hr from HotelReservation hr where hr.room = :room and"  
			+ " (hr.arrivalDate BETWEEN :checkIn and :checkOut or "
			+ "hr.departingDate BETWEEN :checkIn and :checkOut or "
			+ "(hr.arrivalDate < :checkIn and hr.departingDate > :checkOut))"
			+ "and hr.canceled = false")
	List<HotelReservation> checkIfRoomIsAvailableInPeriod(@Param("checkIn") Date checkIn,
			@Param("checkOut") Date checkOut, @Param("room") Room room);

	@Query("select hr from HotelReservation hr where hr.room.hotel = :hotel "
			+ "and hr.status = :status and hr.fastReservation = true "
			+ "and hr.canceled = false")
	List<HotelReservation> findByHotelAndStatus(@Param("hotel") Hotel hotel, @Param("status")ReservationStatus status);

}

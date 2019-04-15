package edu.ftn.isa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.ftn.isa.model.HotelReservation;
import edu.ftn.isa.model.User;

public interface HotelReservationRepository extends JpaRepository<HotelReservation, Long>{

	@Query("SELECT hr FROM HotelReservation hr where "
			+ "hr.user = :user and hr.flightResId is not null")
	List<HotelReservation> findByUserAndBoundedToFlightRes(@Param("user") User user);
	
	@Query("SELECT hr FROM HotelReservation hr where "
			+ "hr.user = :user and hr.flightResId = NULL")
	List<HotelReservation> findSoleReservationsByUser(@Param("user") User user);

}

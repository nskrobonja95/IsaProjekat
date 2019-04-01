package edu.ftn.isa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.ftn.isa.model.FlightReservation;

public interface FlightReservationRepository extends JpaRepository<FlightReservation, Long> {

	@Query("select count(*) from FlightReservation f where f.id.flightId = :flightId")
	int countNumOfReservationsForFlight(@Param("flightId") Long flightId);
	
}

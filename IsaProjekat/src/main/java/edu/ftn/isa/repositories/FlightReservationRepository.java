package edu.ftn.isa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ftn.isa.model.FlightReservation;

public interface FlightReservationRepository extends JpaRepository<FlightReservation, Long> {

	
	
}

package edu.ftn.isa.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.ftn.isa.model.AvioCompany;
import edu.ftn.isa.model.Flight;
import edu.ftn.isa.model.FlightRate;
import edu.ftn.isa.model.FlightReservation;

public interface FlightRateRepository extends JpaRepository<FlightRate, Long> {
	
	@Query("SELECT avg(fr.rate) FROM FlightRate fr where fr.flight.avioCompany = :avio")
	float getAverageRateForAvioCompany(@Param("avio") AvioCompany avio);

	Optional<FlightRate> findByFlightAndFlightReservation(Flight flight, FlightReservation flightReservation);
}

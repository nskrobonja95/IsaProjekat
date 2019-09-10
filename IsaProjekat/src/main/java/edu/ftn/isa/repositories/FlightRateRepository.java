package edu.ftn.isa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.ftn.isa.model.AvioCompany;
import edu.ftn.isa.model.FlightRate;

public interface FlightRateRepository extends JpaRepository<FlightRate, Long> {
	
	@Query("SELECT avg(fr.rate) FROM FlightRate fr where fr.flight.avioCompany = :avio")
	float getAverageRateForAvioCompany(@Param("avio") AvioCompany avio);

}

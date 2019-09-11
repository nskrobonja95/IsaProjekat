package edu.ftn.isa.repositories;

import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.query.Param;

import edu.ftn.isa.model.AvioCompany;
import edu.ftn.isa.model.Destination;
import edu.ftn.isa.model.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long>{

	@Query("SELECT f FROM Flight f where date(f.takeoff) = date(:departDate)"
			+ " and f.from = :from and f.toDest = :to")
	List<Flight> roundTripSearch(@Param("departDate") Date departDate, 
			@Param("from") Destination from, @Param("to") Destination to);
	
	@Query("SELECT f FROM Flight f where date(f.takeoff) = date(:departDate)"
			+ " and f.from = :from and f.toDest = :to")
	List<Flight> oneWaySearch(@Param("departDate") Date departDate, 
			@Param("from") Destination from, @Param("to") Destination to);
	
	List<Flight> findByAvioCompany(AvioCompany avio);
	
	@Query("SELECT AVG(fr.rate) FROM FlightRate fr where fr.flight = :flight "
			+ "and fr.rate != 0")
	float getAverageRatingForFlight(@Param("flight") Flight flight);
	
}

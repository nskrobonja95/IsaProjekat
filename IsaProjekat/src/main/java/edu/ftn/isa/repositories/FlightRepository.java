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
	
	@Query("SELECT avg(fr.rate) FROM FlightRate fr where fr.flight = :flight "
			+ "and fr.rate != 0")
	Float getAverageRatingForFlight(@Param("flight") Flight flight);

	@Query("select f from Flight f where f.from = :d or f.toDest = :d")
	List<Flight> findByDestination(@Param("d")Destination d);
	
}

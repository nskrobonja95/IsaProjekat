package edu.ftn.isa.repositories;

import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.query.Param;

import edu.ftn.isa.model.Destination;
import edu.ftn.isa.model.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long>{

	@Query("SELECT f FROM Flight f where date(f.takeoff) = date(:departDate) and date(f.landing) = date(:returnDate)"
			+ " and f.from = :from and f.toDest like :to")
	List<Flight> roundTripSearch(@Param("departDate") Date departDate, 
			@Param("returnDate") Date returnDate, @Param("from") Destination from, @Param("to") Destination to);
	
}

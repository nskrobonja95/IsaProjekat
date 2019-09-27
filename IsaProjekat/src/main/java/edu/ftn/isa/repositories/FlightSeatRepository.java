package edu.ftn.isa.repositories;

import java.util.List;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.ftn.isa.model.AvioCompany;
import edu.ftn.isa.model.Flight;
import edu.ftn.isa.model.FlightSeat;

public interface FlightSeatRepository extends JpaRepository<FlightSeat, Long>{

	@Query("SELECT COUNT(*) FROM FlightSeat fs where fs.flight = :flight "
			+ "and fs.available = true")
	int countNumOfAvailableSeatsForFlight(@Param("flight")Flight flight);
	
	@Query("SELECT COUNT(*) FROM FlightSeat fs where fs.flight = :flight "
			+ "and fs.available = true and fs.flightClass = 1")
	int countNumOfAvailableEconomicSeatsForFlight(@Param("flight")Flight flight);
	
	@Query("SELECT COUNT(*) FROM FlightSeat fs where fs.flight = :flight "
			+ "and fs.available = true and fs.flightClass = 0")
	int countNumOfAvailableBusinessSeatsForFlight(@Param("flight")Flight flight);
	
	@Lock(LockModeType.PESSIMISTIC_READ)
	List<FlightSeat> findByFlight(Flight f);
	
	@Query("SELECT fs FROM FlightSeat fs where fs.flight.avioCompany = :avio "
			+ "and fs.available = true and fs.fastReservation = true")
	List<FlightSeat> findFastReservationsForAvio(@Param("avio")AvioCompany avio);
	
}

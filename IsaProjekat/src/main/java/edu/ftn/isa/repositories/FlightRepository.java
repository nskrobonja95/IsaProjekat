package edu.ftn.isa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ftn.isa.model.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long>{

}

package edu.ftn.isa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ftn.isa.model.Destination;

public interface DestinationRepository extends JpaRepository<Destination, Long>{

	Destination findByName(String name);
	
}

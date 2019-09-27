package edu.ftn.isa.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ftn.isa.model.RentACarService;

public interface RentACarRepository extends JpaRepository<RentACarService, Long>{

	Optional<RentACarService> findById(Long id);
	
}

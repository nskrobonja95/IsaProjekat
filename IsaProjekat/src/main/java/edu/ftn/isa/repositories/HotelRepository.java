package edu.ftn.isa.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ftn.isa.model.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Long>{

	Optional<Hotel> findById(Long id);
	
}

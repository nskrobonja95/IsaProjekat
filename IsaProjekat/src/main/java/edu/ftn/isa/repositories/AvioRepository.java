package edu.ftn.isa.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ftn.isa.model.AvioCompany;

public interface AvioRepository extends JpaRepository<AvioCompany, Long>{

	Optional<AvioCompany> findByName(String avioName);

	
}

package edu.ftn.isa.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ftn.isa.model.AvioCompany;
import edu.ftn.isa.model.User;

public interface AvioRepository extends JpaRepository<AvioCompany, Long>{

	Optional<AvioCompany> findByName(String avioName);

	AvioCompany findByAdmin(User user);
	
}

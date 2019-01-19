package edu.ftn.isa.repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.ftn.isa.model.AvioCompany;
import edu.ftn.isa.model.Destination;

public interface DestinationRepository extends JpaRepository<Destination, Long>{

	Destination findByName(String name);
	
	List<Destination> findByAvioCompanies(Collection<AvioCompany> avioCompanies);
	
}

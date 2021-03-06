package edu.ftn.isa.repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.ftn.isa.model.AvioCompany;
import edu.ftn.isa.model.Destination;

public interface DestinationRepository extends JpaRepository<Destination, Long>{

	Destination findByNameAndDeleted(String name, boolean deleted);
	
	List<Destination> findByAvioCompanies(Collection<AvioCompany> avioCompanies);
	
	List<Destination> findByDeleted(Boolean deleted);

	@Query("SELECT d FROM Destination d"
			+ " where d not in("
					+ "SELECT dest FROM "
					+ "Destination dest left join dest.avioCompanies ac where ac.id = :avio)")
	List<Destination> findRestOfDestinations(@Param("avio") Long avio);
	
}

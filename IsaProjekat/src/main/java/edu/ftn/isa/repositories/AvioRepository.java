package edu.ftn.isa.repositories;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.ftn.isa.model.AvioCompany;
import edu.ftn.isa.model.User;

public interface AvioRepository extends JpaRepository<AvioCompany, Long>{

	Optional<AvioCompany> findByName(String avioName);

	AvioCompany findByAdmin(User user);
	
	@Query("SELECT COUNT(*) FROM FlightReservation fr where fr.reserveDate = :todayDate")
	Long getNumOfDailySoldTickets(@Param("todayDate") Date todayDate);
	
	@Query("SELECT COUNT(*) FROM FlightReservation fr where "
			+ "fr.reserveDate BETWEEN :from and :todayDate")
	Long getNumOfSoldTicketsInInterval(@Param("from") Date from, @Param("todayDate") Date todayDate);
	
}

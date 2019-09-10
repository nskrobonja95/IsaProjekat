package edu.ftn.isa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.ftn.isa.dto.HotelFlightReservationDTO;
import edu.ftn.isa.model.AvioCompany;
import edu.ftn.isa.model.Destination;
import edu.ftn.isa.model.FlightReservation;
import edu.ftn.isa.model.FlightSeat;
import edu.ftn.isa.model.User;

public interface FlightReservationRepository extends JpaRepository<FlightReservation, Long> {

	@Query("select count(*) from FlightReservation fr join fr.flightReservationSeats frs"
			+ " where frs.flight.id = :flightId")
	int countNumOfReservationsForFlight(@Param("flightId") Long flightId);
	
	@Query("select fr from FlightReservation fr join fr.flightReservationSeats frs"
			+ " on frs.flight.avioCompany = :avio where "
			+ "(frs.flight.toDest = :dest or frs.flight.from = :dest) and "
			+ "frs.flight.takeoff > CURDATE()")
	List<FlightReservation> findAvioCompanyAndDestination(@Param("avio") AvioCompany avio, 
			@Param("dest") Destination dest);
	
	FlightReservation findByFlightReservationSeatsIn(List<FlightSeat> flightReservationSeats);
	
	@Query("select fr from FlightReservation fr where fr.user.id = :userId")
	List<FlightReservation> getUserReservationsList(@Param("userId") Long userId);
	
//	@Query("select fr from FlightReservation fr left join Flight f on f.id = fr.id.flightId where "
//			+ "fr.user = :user and date(f.takeoff) > date(current_date())")
//	List<FlightReservation> findByUser(@Param("user") User user);
	
//	@Query("SELECT new edu.ftn.dto.HotelFlightReservationDTO(fr.flight, fr.flightticketid, fr.seatnumber) "
//			+ "FROM air_company.flight_reservation as fr " + 
//			"where fr.flightticketid not in(SELECT fr.flightticketid " + 
//			"FROM air_company.flight_reservation as fr join air_company.hotel_reservation as hr " + 
//			"on fr.flightticketid = hr.flight_res_id);")
//	List<HotelFlightReservationDTO> findJointReservations(@Param("user") User user);
	
}

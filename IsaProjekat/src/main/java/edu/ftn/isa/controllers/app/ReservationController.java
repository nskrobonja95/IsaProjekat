package edu.ftn.isa.controllers.app;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.ftn.isa.dto.FlightReservationDTO;
import edu.ftn.isa.dto.HotelDTO;
import edu.ftn.isa.dto.HotelReservationDTO;
import edu.ftn.isa.dto.UserFlightReservationDTO;
import edu.ftn.isa.model.FlightClass;
import edu.ftn.isa.model.FlightReservation;
import edu.ftn.isa.model.HotelReservation;
import edu.ftn.isa.model.HotelServiceModel;
import edu.ftn.isa.model.Room;
import edu.ftn.isa.model.User;
import edu.ftn.isa.repositories.FlightRepository;
import edu.ftn.isa.repositories.FlightReservationRepository;
import edu.ftn.isa.repositories.HotelRepository;
import edu.ftn.isa.repositories.HotelReservationRepository;
import edu.ftn.isa.repositories.HotelServicesRepository;
import edu.ftn.isa.repositories.RoomRepository;
import edu.ftn.isa.repositories.UserRepository;
import edu.ftn.isa.security.CustomUserDetails;
import edu.ftn.isa.services.ReservationService;

@RestController
@RequestMapping("/reserve")
@CrossOrigin
public class ReservationController {
	
	@Autowired
	private FlightReservationRepository flightResRepo;
	
	@Autowired
	private ReservationService resService;
	
	
	@GetMapping("/flightUserReservationsList")
	public ResponseEntity<?> getUserFlightReservations(){
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
		User user = userDetails.getUser();
		
		List<FlightReservation> reservationsList = flightResRepo.getUserReservationsList(user.getId());
		List<UserFlightReservationDTO> retVal = new ArrayList<UserFlightReservationDTO>();
		for(FlightReservation reservation : reservationsList) {
			retVal.add(new UserFlightReservationDTO(reservation.getId(),
					reservation.getFlightReservationSeats(),
					reservation.getRate(),
					reservation.getName(),
					reservation.getLastname(),
					reservation.getStatus().toString()));
		}
		
		return new ResponseEntity<List<UserFlightReservationDTO>>(retVal, HttpStatus.OK);
	}
	
//	@PostMapping("/flight")
//	public ResponseEntity<?> reserveFlight(
//			@RequestBody List<FlightReservationDTO> reservationsDto) {
//		
//		FlightReservation reservation = new FlightReservation();
//			
//		for(FlightReservationDTO reservationDto : reservationsDto) {
//			if(reservationDto.getName() == null) {
//				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//				CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
//				User user = userDetails.getUser();
//				reservation.setName(user.getName());
//				reservation.setLastname(user.getLastname());
//			} else {
//				reservation.setName(reservationDto.getName());
//				reservation.setLastname(reservationDto.getLastname());
//			}
//			
//			//reservation.setFlight(flightRepo.findById(reservationDto.getFlightId()).get());
////			reservation.setCanceled(false);
//			reservation.setReserveDate(new Date());
//			//reservation.setSeatNumber(reservationDto.getSeatNumber());
//			
////			if(reservationDto.getFlightClass().equals("Economic")) {
////				reservation.setFlightClass(FlightClass.Economic);
////			} else {
////				reservation.setFlightClass(FlightClass.Bussiness);
////			}
//		}
//			flightResRepo.save(reservation);
//		
//		return new ResponseEntity<>(HttpStatus.OK);
//	}
	
}

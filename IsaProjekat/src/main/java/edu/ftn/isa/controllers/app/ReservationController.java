package edu.ftn.isa.controllers.app;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.ftn.isa.dto.FlightReservationDTO;
import edu.ftn.isa.dto.HotelReservationDTO;
import edu.ftn.isa.model.FlightClass;
import edu.ftn.isa.model.FlightReservation;
import edu.ftn.isa.model.HotelReservation;
import edu.ftn.isa.model.HotelService;
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

@RestController
@RequestMapping("/reserve")
public class ReservationController {

	@Autowired
	private HotelRepository hotelRepo;
	
	@Autowired
	private HotelReservationRepository hotelResRepo;
	
	@Autowired
	private HotelServicesRepository hotelServRepo;
	
	@Autowired
	private RoomRepository roomRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private FlightRepository flightRepo;
	
	@Autowired
	private FlightReservationRepository flightResRepo;
	
	@PostMapping("/room")
	public ResponseEntity<?> reserveRoom(@RequestBody HotelReservationDTO reservationDto) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		
		Room room = roomRepo.findById(reservationDto.getRoomId()).get();
		
		HotelReservation reservation = new HotelReservation();
		reservation.setCanceled(false);
		reservation.setUser(userDetails.getUser());
		reservation.setRoom(room);
//		reservation.setFlightResId(reservationDto.getFlightResId());
		reservation.setArrivalDate(reservationDto.getArrivalDate());
		reservation.setDepartingDate(reservationDto.getDepartingDate());
		List<HotelService> services = new ArrayList<HotelService>();
		for(HotelService service : room.getHotelServices()) {
			if(reservationDto.getHotelServices().contains(service.getName()))
				services.add(service);
		}
		reservation.setServices(services);
		
		hotelResRepo.save(reservation);
		return new ResponseEntity<Long>(355L, HttpStatus.OK);
	}
	
	@PostMapping("/flight")
	public ResponseEntity<?> reserveFlight(
			@RequestBody List<FlightReservationDTO> reservationsDto) {
		
		FlightReservation reservation = new FlightReservation();
			
		for(FlightReservationDTO reservationDto : reservationsDto) {
			if(reservationDto.getName() == null) {
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
				User user = userDetails.getUser();
				reservation.setName(user.getName());
				reservation.setLastname(user.getLastname());
			} else {
				reservation.setName(reservationDto.getName());
				reservation.setLastname(reservationDto.getLastname());
			}
			
			//reservation.setFlight(flightRepo.findById(reservationDto.getFlightId()).get());
			reservation.setCanceled(false);
			reservation.setReserveDate(new Date());
			//reservation.setSeatNumber(reservationDto.getSeatNumber());
			
//			if(reservationDto.getFlightClass().equals("Economic")) {
//				reservation.setFlightClass(FlightClass.Economic);
//			} else {
//				reservation.setFlightClass(FlightClass.Bussiness);
//			}
		}
			flightResRepo.save(reservation);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}

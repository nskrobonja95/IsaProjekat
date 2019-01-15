package edu.ftn.isa.controllers.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.ftn.isa.dto.HotelReservationDTO;
import edu.ftn.isa.model.Hotel;
import edu.ftn.isa.model.HotelReservation;
import edu.ftn.isa.model.HotelService;
import edu.ftn.isa.model.Room;
import edu.ftn.isa.model.User;
import edu.ftn.isa.repositories.HotelRepository;
import edu.ftn.isa.repositories.HotelReservationRepository;
import edu.ftn.isa.repositories.HotelServicesRepository;
import edu.ftn.isa.repositories.RoomRepository;
import edu.ftn.isa.repositories.UserRepository;

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
	
	@PostMapping("/room/{id}")
	public ResponseEntity<?> reserveRoom(@RequestBody HotelReservationDTO reservationDto) {
		
		Optional<Hotel> hotel = hotelRepo.findById(reservationDto.getHotelId());
		Optional<Room> room = roomRepo.findById(reservationDto.getRoomId());
        Optional<User> user = userRepo.findById(reservationDto.getUserId());
		
		HotelReservation reservation = new HotelReservation();
		reservation.setCanceled(false);
		reservation.setHotel(hotel.get());
		reservation.setRoom(room.get());
		reservation.setUser(user.get());
		reservation.setArrivalDate(reservationDto.getArrivalDate());
		reservation.setDepartingDate(reservationDto.getDepartingDate());
		List<HotelService> services = new ArrayList<HotelService>();
		
		for(int i=0; i<reservationDto.getHotelServiceNames().size(); ++i) {
			HotelService service = hotelServRepo.findByNameAndHotel(reservationDto.getHotelServiceNames().get(i), reservation.getHotel());
			if(service != null)
				services.add(service);
		}
		reservation.setServices(services);
		
		HotelReservation savedRes = hotelResRepo.save(reservation);
		savedRes.getHotel().setRooms(null);
		savedRes.getRoom().setHotel(null);
		for(HotelService service: savedRes.getServices()) {
			service.setReservations(null);
		}
		return new ResponseEntity<HotelReservation>(savedRes, HttpStatus.OK);
	}
	
}

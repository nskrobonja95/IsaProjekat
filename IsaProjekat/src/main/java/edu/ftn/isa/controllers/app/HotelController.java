package edu.ftn.isa.controllers.app;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.ftn.isa.converters.HotelServicesConverter;
import edu.ftn.isa.model.Hotel;
import edu.ftn.isa.model.HotelServiceModel;
import edu.ftn.isa.model.Room;
import edu.ftn.isa.repositories.HotelRepository;
import edu.ftn.isa.repositories.HotelServicesRepository;
import edu.ftn.isa.repositories.RoomRepository;

@RestController
@RequestMapping("/hotel")
public class HotelController {

	@Autowired
	private HotelRepository hotelRepo;
	
	@Autowired
	private HotelServicesRepository hotelServRepo;
	
	@Autowired
	private RoomRepository roomRepo;
	
	@Autowired
	private HotelServicesConverter hotelServicesConverter;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getHotel(
			@PathVariable("id") Long id) {
		
		Optional<Hotel> optionalHotel = hotelRepo.findById(id);
		if(!optionalHotel.isPresent())
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<Hotel>(optionalHotel.get(), HttpStatus.OK);
		
	}
	
	@GetMapping("/rooms/{id}")
	public ResponseEntity<?> getHotelRooms(
			@PathVariable("id") Long hotelId) {
			Optional<Hotel> opHotel = hotelRepo.findById(hotelId);
			if(!opHotel.isPresent())
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			Hotel hotel = opHotel.get();
			List<Room> rooms = roomRepo.findByHotel(hotel);
			return new ResponseEntity<List<Room>>(rooms, HttpStatus.OK);
	}
	
	@GetMapping("/services/{id}")
	public ResponseEntity<?> getHotelService(
			@PathVariable("id") Long hotelid) {
		Optional<Hotel> hotel = hotelRepo.findById(hotelid);
		List<HotelServiceModel> services = hotelServRepo.findByHotel(hotel.get());
		return new ResponseEntity<List<HotelServiceModel>>(services, HttpStatus.OK);
		
	}
	
}

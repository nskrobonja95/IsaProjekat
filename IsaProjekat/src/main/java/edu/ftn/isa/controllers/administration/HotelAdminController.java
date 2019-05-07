package edu.ftn.isa.controllers.administration;

import java.util.List;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.ftn.isa.dto.AddHotelServiceDTO;
import edu.ftn.isa.dto.BasicHotelInfoDTO;
import edu.ftn.isa.model.Hotel;
import edu.ftn.isa.model.HotelService;
import edu.ftn.isa.model.Room;
import edu.ftn.isa.repositories.HotelRepository;
import edu.ftn.isa.repositories.HotelServicesRepository;
import edu.ftn.isa.repositories.RoomRepository;
import edu.ftn.isa.security.CustomUserDetails;

@RestController
@RequestMapping("/hoteladmin")
public class HotelAdminController {
	
	@Autowired
	private HotelRepository hotelRepo;
	
	@Autowired
	private HotelServicesRepository hotelServicesRepo;
	
	@Autowired
	private RoomRepository roomRepo;

	@GetMapping("/getHotel")
	public ResponseEntity<?> getAvioCompany() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		Hotel retVal = hotelRepo.findByAdmin(userDetails.getUser());
		return new ResponseEntity<Hotel>(retVal, HttpStatus.OK);
	}
	
	@PutMapping("/updateBasicHotelInfo")
	public ResponseEntity<?> updateBasicHotelInfo(@RequestBody BasicHotelInfoDTO hotelDto) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		Hotel retVal = hotelRepo.findByAdmin(userDetails.getUser());
		retVal.setName(hotelDto.getName());
		retVal.setAddress(hotelDto.getAddress());
		retVal.setPromo(hotelDto.getPromo());
		hotelRepo.save(retVal);
		return new ResponseEntity<Hotel>(retVal, HttpStatus.OK);
	}
	
	@GetMapping("/getServices")
	public ResponseEntity<?> getServices() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		Hotel hotel = hotelRepo.findByAdmin(userDetails.getUser());
		List<HotelService> hotelServices = hotelServicesRepo.findByHotel(hotel);
		return new ResponseEntity<List<HotelService>>(hotelServices, HttpStatus.OK);
	}
	
	@PostMapping("/saveService")
	public ResponseEntity<?> saveService(@RequestBody AddHotelServiceDTO serviceDto) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		Hotel hotel = hotelRepo.findByAdmin(userDetails.getUser());
		HotelService hotelService = new HotelService();
		hotelService.setHotel(hotel);
		hotelService.setName(serviceDto.getName());
		hotelService.setRate((double) serviceDto.getRate());
		hotelService.setCharge(serviceDto.getCharge());
		hotelServicesRepo.save(hotelService);
		return new ResponseEntity<HotelService>(hotelService, HttpStatus.OK);
	}
	
	@DeleteMapping("removeService/{id}")
	public ResponseEntity<?> removeService(@PathVariable("id") Long id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		Hotel hotel = hotelRepo.findByAdmin(userDetails.getUser());
		hotelServicesRepo.deleteById(id);
		return new ResponseEntity<List<HotelService>>(hotelServicesRepo.findByHotel(hotel), HttpStatus.OK);
	}
	
	@GetMapping("/getRooms")
	public ResponseEntity<?> getRooms() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		Hotel hotel = hotelRepo.findByAdmin(userDetails.getUser());
		List<Room> rooms = roomRepo.findByHotel(hotel);
		return new ResponseEntity<List<Room>>(rooms, HttpStatus.OK);
	}
	
}

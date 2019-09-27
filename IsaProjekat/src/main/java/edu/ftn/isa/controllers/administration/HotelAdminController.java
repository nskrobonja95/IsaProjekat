package edu.ftn.isa.controllers.administration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
import edu.ftn.isa.dto.AvioStatisticsDTO;
import edu.ftn.isa.dto.BasicHotelInfoDTO;
import edu.ftn.isa.dto.CreateRoomDTO;
import edu.ftn.isa.dto.EditRoomDTO;
import edu.ftn.isa.dto.FastRoomReservationDTO;
import edu.ftn.isa.dto.HotelStatisticsDTO;
import edu.ftn.isa.dto.PriceOfMonthDTO;
import edu.ftn.isa.model.Hotel;
import edu.ftn.isa.model.HotelServiceModel;
import edu.ftn.isa.model.PriceOfRoom;
import edu.ftn.isa.model.Room;
import edu.ftn.isa.repositories.HotelRepository;
import edu.ftn.isa.repositories.HotelServicesRepository;
import edu.ftn.isa.repositories.PriceOfRoomRepository;
import edu.ftn.isa.repositories.RoomRepository;
import edu.ftn.isa.security.CustomUserDetails;
import edu.ftn.isa.services.HotelService;
import edu.ftn.isa.services.HotelServiceService;
import edu.ftn.isa.services.RoomService;
import edu.ftn.isa.services.StatsService;

@RestController
@RequestMapping("/hoteladmin")
public class HotelAdminController {
	
	@Autowired
	private HotelRepository hotelRepo;
	
	@Autowired
	private HotelService hotelService;
	
	@Autowired
	private HotelServicesRepository hotelServicesRepo;
	
	@Autowired
	private HotelServiceService hotelServiceService;
	
	@Autowired
	private RoomRepository roomRepo;
	
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private PriceOfRoomRepository priceOfRoomRepo;
	
	@Autowired
	private StatsService statsService;

	@GetMapping("/getHotel/{id}")
	public ResponseEntity<?> getHotel(@PathVariable("id") Long id) {
		Hotel hotel = hotelService.findHotelById(id);
		if(hotel == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<Hotel>(hotel, HttpStatus.OK);
	}
	@GetMapping("/getHotels")
	public ResponseEntity<?> getHotels() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		List<Hotel> hotel = hotelService.findHotelsByAdmin(userDetails.getUser());
		if(hotel == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<List<Hotel>>(hotel, HttpStatus.OK);
	}
	@PutMapping("/updateBasicHotelInfo")
	public ResponseEntity<?> updateBasicHotelInfo(@RequestBody BasicHotelInfoDTO hotelDto) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		Hotel retVal = hotelService.updateBasicHotelInfo(userDetails.getUser(), hotelDto);
		if(retVal == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<Hotel>(retVal, HttpStatus.OK);
	}
	
	@GetMapping("/getServices/{id}")
	public ResponseEntity<?> getServices(@PathVariable("id") Long id) {
		List<HotelServiceModel> hotelServices = hotelServiceService.findByHotel(id);
		if(hotelServices == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<List<HotelServiceModel>>(hotelServices, HttpStatus.OK);
	}
	
	@PostMapping("/saveService/{id}")
	public ResponseEntity<?> saveService(@PathVariable("id") Long id, @RequestBody AddHotelServiceDTO serviceDto) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		HotelServiceModel hotelService = hotelServiceService.saveService(id, serviceDto);
		if(hotelService == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<HotelServiceModel>(hotelService, HttpStatus.OK);
	}
	
	@DeleteMapping("removeService/{serviceId}/{hotelId}")
	public ResponseEntity<?> removeService(@PathVariable("serviceId") Long serviceId, @PathVariable("hotelId") Long hotelId) {
		
		List<HotelServiceModel> services = hotelServiceService.removeService(serviceId, hotelId);
		if(services == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<List<HotelServiceModel>>(services, HttpStatus.OK);
	}
	
	@GetMapping("/getRooms/{id}")
	public ResponseEntity<?> getRooms(@PathVariable("id") Long id) {
		List<Room> rooms = roomService.getRoomsForAdmin(id);
		return new ResponseEntity<List<Room>>(rooms, HttpStatus.OK);
	}
	
	@PostMapping("/saveRoom/{id}")
	public ResponseEntity<?> saveRoom(@RequestBody CreateRoomDTO roomDto, @PathVariable("id") Long id) throws ParseException {
		System.out.println("Usao ovde:" + id);

		Room savedRoom = roomService.saveRoom(roomDto, id);
		if(savedRoom == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("/editRoom")
	public ResponseEntity<?> editRoom(@RequestBody EditRoomDTO room) throws ParseException {
		
		Room retVal = roomService.editRoom(room);
		if(retVal == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteRoom/{id}")
	public ResponseEntity<?> deleteRoom(@PathVariable("id") Long id) {
		if(!roomService.deleteRoom(id))
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/getRoom/{roomId}")
	public ResponseEntity<?> getRoomById(@PathVariable("roomId") Long id) {
		Room room = roomService.getRoomById(id);
		if(room == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<Room>(room, HttpStatus.OK);
	}
	
	@GetMapping("/loadHotelServicesByRoomId/{roomId}")
	public ResponseEntity<?> loadHotelServicesByRoomId(@PathVariable("roomId") Long id) {
		List<HotelServiceModel> services = hotelServiceService.loadHotelServicesByRoomId(id);
		if(services == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<List<HotelServiceModel>>(services, HttpStatus.OK);
	}
	
	@PostMapping("/createFastReservation/{roomId}")
	public ResponseEntity<?> createFastReservation(@PathVariable("roomId") Long id,
			@RequestBody FastRoomReservationDTO dto) throws ParseException {
		Integer retVal = hotelService.createFastReservation(id, dto); 
		if(retVal == -1)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}
	@GetMapping("/hotelStatistics/{hotelId}")
	public ResponseEntity<?> getHotelStats(@PathVariable("hotelId") Long hotelId) throws ParseException{
		
		HotelStatisticsDTO stats = statsService.getHotelStats(hotelId);
		if(stats == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<HotelStatisticsDTO>(stats,HttpStatus.OK);
	}
	
}

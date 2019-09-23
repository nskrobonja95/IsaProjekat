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
import edu.ftn.isa.dto.BasicHotelInfoDTO;
import edu.ftn.isa.dto.CreateRoomDTO;
import edu.ftn.isa.dto.EditRoomDTO;
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

	@GetMapping("/getHotel")
	public ResponseEntity<?> getAvioCompany() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		Hotel hotel = hotelService.findHotelByAdmin(userDetails.getUser());
		if(hotel == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<Hotel>(hotel, HttpStatus.OK);
	}
	
	@PutMapping("/updateBasicHotelInfo")
	public ResponseEntity<?> updateBasicHotelInfo(@RequestBody BasicHotelInfoDTO hotelDto) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		Hotel retVal = hotelService.updateBasicHotelInfo(userDetails.getUser(), hotelDto);
		if(retVal == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<Hotel>(retVal, HttpStatus.OK);
	}
	
	@GetMapping("/getServices")
	public ResponseEntity<?> getServices() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		List<HotelServiceModel> hotelServices = hotelServiceService.findByHotel(userDetails.getUser());
		if(hotelServices == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<List<HotelServiceModel>>(hotelServices, HttpStatus.OK);
	}
	
	@PostMapping("/saveService")
	public ResponseEntity<?> saveService(@RequestBody AddHotelServiceDTO serviceDto) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		HotelServiceModel hotelService = hotelServiceService.saveService(userDetails.getUser(), serviceDto);
		if(hotelService == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<HotelServiceModel>(hotelService, HttpStatus.OK);
	}
	
	@DeleteMapping("removeService/{id}")
	public ResponseEntity<?> removeService(@PathVariable("id") Long id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		List<HotelServiceModel> services = hotelServiceService.removeService(id, userDetails.getUser());
		if(services == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<List<HotelServiceModel>>(services, HttpStatus.OK);
	}
	
	@GetMapping("/getRooms")
	public ResponseEntity<?> getRooms() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		List<Room> rooms = roomService.getRoomsForAdmin(userDetails.getUser());
		return new ResponseEntity<List<Room>>(rooms, HttpStatus.OK);
	}
	
	@PostMapping("/saveRoom")
	public ResponseEntity<?> saveRoom(@RequestBody CreateRoomDTO roomDto) throws ParseException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
//		Hotel hotel = hotelRepo.findByAdmin(userDetails.getUser());
//		Room room = new Room();
//		room.setHotel(hotel);
//		room.setBalcony(roomDto.isBalcony());
//		room.setDescription(roomDto.getDescription());
//		room.setNumOfBeds(roomDto.getNumOfBeds());
//		List<HotelServiceModel> services = new ArrayList<HotelServiceModel>();
//		for(int i=0; i<roomDto.getServices().size(); ++i) {
//			HotelServiceModel hs = hotelServicesRepo.
//								retrieveByNameAndHotel(roomDto.getServices().get(i), 
//														hotel.getId());
//			services.add(hs);
//		}
//		roomRepo.save(room);
//		Calendar now = Calendar.getInstance();
//		int year = now.get(Calendar.YEAR);
//		String yearInString = String.valueOf(year);
//		for(int i=0; i<roomDto.getMonthPrices().size(); ++i) {
//			PriceOfMonthDTO p = roomDto.getMonthPrices().get(i);
//			Date from = new SimpleDateFormat(
//					"yyyy-MM-dd HH:mm").
//						parse(yearInString + p.getFrom());
//			Date to = new SimpleDateFormat(
//					"yyyy-MM-dd HH:mm").
//						parse(yearInString + p.getTo());
//			PriceOfRoom price = new PriceOfRoom();
//			price.setActiveFrom(from);
//			price.setActiveTo(to);
//			price.setPrice(p.getPrice());
//			price.setRoom(room);
//			priceOfRoomRepo.save(price);
//		}
		Room savedRoom = roomService.saveRoom(roomDto, userDetails.getUser());
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
	
}

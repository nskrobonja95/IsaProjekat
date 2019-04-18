package edu.ftn.isa.controllers.app;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.ftn.isa.dto.AvioCompanyDTO;
import edu.ftn.isa.dto.DestinationDTO;
import edu.ftn.isa.dto.HotelDTO;
import edu.ftn.isa.dto.MultiCitySearchDTO;
import edu.ftn.isa.dto.RentACarServiceDTO;
import edu.ftn.isa.dto.RoundTripFlights;
import edu.ftn.isa.dto.RoundTripSearchDTO;
import edu.ftn.isa.dto.SearchAvailableRoomsForHotelDTO;
import edu.ftn.isa.dto.SearchHotelRequestDTO;
import edu.ftn.isa.dto.SearchHotelResponseDTO;
import edu.ftn.isa.model.AvioCompany;
import edu.ftn.isa.model.Destination;
import edu.ftn.isa.model.Flight;
import edu.ftn.isa.model.Hotel;
import edu.ftn.isa.model.HotelService;
import edu.ftn.isa.model.RentACarService;
import edu.ftn.isa.model.Room;
import edu.ftn.isa.repositories.AvioRepository;
import edu.ftn.isa.repositories.DestinationRepository;
import edu.ftn.isa.repositories.FlightRepository;
import edu.ftn.isa.repositories.FlightReservationRepository;
import edu.ftn.isa.repositories.FlightSeatRepository;
import edu.ftn.isa.repositories.HotelRepository;
import edu.ftn.isa.repositories.HotelServicesRepository;
import edu.ftn.isa.repositories.RentACarRepository;
import edu.ftn.isa.repositories.RoomRepository;

@RestController
@RequestMapping("/app")
public class UnregisteredUsersController {

	@Autowired
	private AvioRepository avioRepo;
	
	@Autowired
	private FlightRepository flightRepo;
	
	@Autowired
	private FlightReservationRepository flightResRepo;
	
	@Autowired
	private HotelRepository hotelRepo;
	
	@Autowired
	private RentACarRepository rentACarRepo;
	
	@Autowired
	private DestinationRepository destRepo;
	
	@Autowired
	private RoomRepository roomRepo;
	
	@Autowired
	private HotelServicesRepository hsRepo;
	
	@Autowired
	private FlightSeatRepository flightSeatRepo;
	
	@GetMapping("/airlines")
	public ResponseEntity<?> getAllAirlines() {
		List<AvioCompany> avios = avioRepo.findAll();
		List<AvioCompanyDTO> retVal = new ArrayList<AvioCompanyDTO>();
		AvioCompanyDTO temp;
		for(AvioCompany avio : avios) {
			//System.out.println(avio);
			temp = new AvioCompanyDTO();
			temp.setId(avio.getId());
			temp.setAddress(avio.getAddress());
			temp.setName(avio.getName());
			temp.setPromo(avio.getPromo());
			temp.setAdmin(avio.getAdmin());
			retVal.add(temp);
		}
		return new ResponseEntity<List<AvioCompanyDTO>>(retVal, HttpStatus.OK);
	}
	@GetMapping("airlines/{id}")
	public ResponseEntity<?> getAvio(
			@PathVariable("id") Long id) {
		Optional<AvioCompany> optionalAvio = avioRepo.findById(id);
		//System.out.println(optionalAvio);
		if(!optionalAvio.isPresent())
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<AvioCompany>(optionalAvio.get(), HttpStatus.OK);
	}
	@GetMapping("hotels/{id}")
	public ResponseEntity<?> getHotel(
			@PathVariable("id") Long id) {
		
		Optional<Hotel> optionalHotel = hotelRepo.findById(id);
		if(!optionalHotel.isPresent())
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<Hotel>(optionalHotel.get(), HttpStatus.OK);
		
	}
	@GetMapping("getAllDestinations")
	public ResponseEntity<?> getAllDestinations(){
		List<Destination> allDests = destRepo.findAll();
		List<DestinationDTO> retVal = new ArrayList<DestinationDTO>();
		for(Destination d : allDests) {
			retVal.add(new DestinationDTO(d.getName()));
			
		}
		
		return new ResponseEntity<List<DestinationDTO>>(retVal, HttpStatus.OK);

		
		
	}
	@GetMapping("/getAllDestinationsById/{id}")
	public ResponseEntity<?> getDestinationsOfAvioCompany(
			@PathVariable("id") Long avioId) {
		Optional<AvioCompany> avio = avioRepo.findById(avioId);
		//System.out.println("Ovo su avio kompanije:"+avio);
		if(!avio.isPresent())
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		AvioCompany avioComp = avio.get();
		List<DestinationDTO> retVal = new ArrayList<DestinationDTO>();
		for(Destination d: avioComp.getDestinations()) {
			//System.out.println("ovo su destinacije:"+d);
			DestinationDTO dest = new DestinationDTO();
			dest.setName(d.getName());
			retVal.add(dest);
		}
		return new ResponseEntity<List<DestinationDTO>>(retVal, HttpStatus.OK);
	}
	
	@GetMapping("/getAllDestinations/{name}")
	public ResponseEntity<?> getDestinationsOfAvioCompanyByName(
			@PathVariable("name") String avioName) {
		Optional<AvioCompany> avio = avioRepo.findByName(avioName);
		if(!avio.isPresent())
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		AvioCompany avioComp = avio.get();
		List<DestinationDTO> retVal = new ArrayList<DestinationDTO>();
		for(Destination d: avioComp.getDestinations()) {
			DestinationDTO dest = new DestinationDTO();
			dest.setName(d.getName());
			retVal.add(dest);
		}
		return new ResponseEntity<List<DestinationDTO>>(retVal, HttpStatus.OK);
	}
	
	@GetMapping("/hotels")
	public ResponseEntity<?> getAllHotels() {
		List<Hotel> hotels = hotelRepo.findAll();
		List<HotelDTO> retVal = new ArrayList<HotelDTO>();
		
		for(Hotel hotel : hotels) {
			retVal.add(HotelDTO.parseHotel(hotel));
		}
		return new ResponseEntity<List<HotelDTO>>(retVal, HttpStatus.OK);
	}
	
	@GetMapping("/carHire")
	public ResponseEntity<?> getAllRentACar() {
		List<RentACarService> rentACars = rentACarRepo.findAll();
		List<RentACarServiceDTO> retVal = new ArrayList<RentACarServiceDTO>();
		
		for(RentACarService rentACar : rentACars) {
			retVal.add(RentACarServiceDTO.parseRentACar(rentACar));
		}
		return new ResponseEntity<List<RentACarServiceDTO>>(retVal, HttpStatus.OK);
	}
	
	@PostMapping("/roundTripSearch")
	public ResponseEntity<?> searchFlights(@RequestBody RoundTripSearchDTO searchDto) throws ParseException {
		Date departDate = new SimpleDateFormat("yyyy-MM-dd").parse(searchDto.getDepartDate());
		Date returnDate = new SimpleDateFormat("yyyy-MM-dd").parse(searchDto.getReturnDate());
		Destination from = destRepo.findByName(searchDto.getFrom());
		Destination to = destRepo.findByName(searchDto.getTo());
		List<Flight> filteredFlights = flightRepo.roundTripSearch(departDate, from, to);
		List<Flight> filteredReturnFlights = flightRepo.roundTripSearch(returnDate, to, from);
		List<Flight> retValFlights = new ArrayList<Flight>();
		List<Flight> retValReturnFlights = new ArrayList<Flight>();
		for(Flight f : filteredFlights) {
			int numOfAvailableSeats = flightSeatRepo.countNumOfAvailableSeatsForFlight(f);
			if(numOfAvailableSeats >= searchDto.getNumOfPpl())
				retValFlights.add(f);
//			int numOfRes = flightResRepo.countNumOfReservationsForFlight(f.getId());
//			if(numOfRes < f.getNumberOfSeats()) {
//				int availableSeats = f.getNumberOfSeats() - numOfRes;
//				if(availableSeats >= searchDto.getNumOfPpl())
//					retValFlights.add(f);
//			}
		}
		for(Flight f : filteredReturnFlights) {
			int numOfAvailableSeats = flightSeatRepo.countNumOfAvailableSeatsForFlight(f);
			if(numOfAvailableSeats >= searchDto.getNumOfPpl())
				retValReturnFlights.add(f);
//			int numOfRes = flightResRepo.countNumOfReservationsForFlight(f.getId());
//			if(numOfRes < f.getNumberOfSeats()) {
//				int availableSeats = f.getNumberOfSeats() - numOfRes;
//				if(availableSeats >= searchDto.getNumOfPpl())
//					retValReturnFlights.add(f);
//			}
		}
		RoundTripFlights retVal = new RoundTripFlights();
		retVal.setDirectFlights(retValFlights);
		retVal.setReturnFlights(retValReturnFlights);
		return new ResponseEntity<RoundTripFlights>(retVal, HttpStatus.OK);
	}
	
	@PostMapping("/oneWaySearch")
	public ResponseEntity<?> searchFlightsOneWay(@RequestBody RoundTripSearchDTO searchDto) throws ParseException {
		Date takeoff = new SimpleDateFormat("yyyy-MM-dd").parse(searchDto.getDepartDate());
		List<Flight> filteredFlights = flightRepo.oneWaySearch(takeoff, destRepo.findByName(searchDto.getFrom()), destRepo.findByName(searchDto.getTo()));
		List<Flight> retVal = new ArrayList<Flight>();
		for(Flight f : filteredFlights) {
			int numOfAvailableSeats = flightSeatRepo.countNumOfAvailableSeatsForFlight(f);
			if(numOfAvailableSeats >= searchDto.getNumOfPpl())
				retVal.add(f);
//			int numOfRes = flightResRepo.countNumOfReservationsForFlight(f.getId());
//			if(numOfRes < f.getNumberOfSeats()) {
//				int availableSeats = f.getNumberOfSeats() - numOfRes;
//				if(availableSeats >= searchDto.getNumOfPpl())
//					retVal.add(f);
//			}
		}
		return new ResponseEntity<List<Flight>>(retVal, HttpStatus.OK);
	}
	
	@PostMapping("/multiCitySearch")
	public ResponseEntity<?> searchFlightsMultiCity(@RequestBody MultiCitySearchDTO searchDto) throws ParseException {
		Date takeoff1 = new SimpleDateFormat("yyyy-MM-dd").parse(searchDto.getDepartDate1());
		Date takeoff2 = new SimpleDateFormat("yyyy-MM-dd").parse(searchDto.getDepartDate2());
		Destination from = destRepo.findByName(searchDto.getFrom());
		Destination midDest = destRepo.findByName(searchDto.getMidDest());
		Destination to = destRepo.findByName(searchDto.getTo());
		List<Flight> filteredFlights = flightRepo.oneWaySearch(takeoff1, from, midDest);
		List<Flight> filteredFlights2 = flightRepo.oneWaySearch(takeoff2, midDest, to);
		List<Flight> retValFlights = new ArrayList<Flight>();
		List<Flight> retValFlights2 = new ArrayList<Flight>();
		for(Flight f : filteredFlights) {
			int numOfAvailableSeats = flightSeatRepo.countNumOfAvailableSeatsForFlight(f);
			if(numOfAvailableSeats >= searchDto.getNumOfPpl())
				retValFlights.add(f);
			int numOfRes = flightResRepo.countNumOfReservationsForFlight(f.getId());
//			if(numOfRes < f.getNumberOfSeats()) {
//				int availableSeats = f.getNumberOfSeats() - numOfRes;
//				if(availableSeats >= searchDto.getNumOfPpl())
//					retValFlights.add(f);
//			}
		}
		for(Flight f : filteredFlights2) {
			int numOfAvailableSeats = flightSeatRepo.countNumOfAvailableSeatsForFlight(f);
			if(numOfAvailableSeats >= searchDto.getNumOfPpl())
				retValFlights2.add(f);
//			int numOfRes = flightResRepo.countNumOfReservationsForFlight(f.getId());
//			if(numOfRes < f.getNumberOfSeats()) {
//				int availableSeats = f.getNumberOfSeats() - numOfRes;
//				if(availableSeats >= searchDto.getNumOfPpl())
//					retValFlights2.add(f);
//			}
		}
		
		RoundTripFlights retVal = new RoundTripFlights();
		retVal.setDirectFlights(filteredFlights);
		retVal.setReturnFlights(filteredFlights2);
		return new ResponseEntity<RoundTripFlights>(retVal, HttpStatus.OK);
	}
	
	@PostMapping("/searchAvailableRooms")
	public ResponseEntity<?> searchRooms(@RequestBody SearchHotelRequestDTO searchDto) throws ParseException {
		Date checkInDate = new SimpleDateFormat("yyyy-MM-dd").parse(searchDto.getCheckIn());
		Date checkOutDate = new SimpleDateFormat("yyyy-MM-dd").parse(searchDto.getCheckOut());
		List<Room> availableRooms = roomRepo.searchAvailableRooms(checkInDate, checkOutDate, destRepo.findByName(searchDto.getDest()));
		return new ResponseEntity<List<Room>>(availableRooms, HttpStatus.OK);
	}
	
	@PostMapping("/searchHotels")
	public ResponseEntity<?> searchHotels(@RequestBody SearchHotelRequestDTO searchDto) throws ParseException {
		Date checkInDate = new SimpleDateFormat("yyyy-MM-dd").parse(searchDto.getCheckIn());
		Date checkOutDate = new SimpleDateFormat("yyyy-MM-dd").parse(searchDto.getCheckOut());
		List<Hotel> availableHotels = hotelRepo.searchAvailableHotels(checkInDate, checkOutDate, destRepo.findByName(searchDto.getDest()));
		SearchHotelResponseDTO response = new SearchHotelResponseDTO();
		response.setCheckIn(searchDto.getCheckIn());
		response.setCheckOut(searchDto.getCheckOut());
		response.setHotels(availableHotels);
		return new ResponseEntity<SearchHotelResponseDTO>(response, HttpStatus.OK);
	}
	
	@PostMapping("/searchAvailableRoomsForHotel")
	public ResponseEntity<?> searchRooms(@RequestBody SearchAvailableRoomsForHotelDTO searchDto) throws ParseException {
		Date checkInDate = null;
		Date checkOutDate = null;
		try {
			checkInDate = new SimpleDateFormat("yyyy-MM-dd").parse(searchDto.getCheckIn());
			checkOutDate = new SimpleDateFormat("yyyy-MM-dd").parse(searchDto.getCheckOut());
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		List<Room> availableRooms = roomRepo.searchAvailableRoomsForHotel(checkInDate, checkOutDate, searchDto.getHotel());
		return new ResponseEntity<List<Room>>(availableRooms, HttpStatus.OK);
	}
	
	@GetMapping("/loadHotelServices/{hotelId}")
	public ResponseEntity<?> loadHotelServices(@PathVariable("hotelId") Long hotelId) {
		Hotel h = hotelRepo.findById(hotelId).get();
		List<HotelService> services = hsRepo.findByHotel(h);
		return new ResponseEntity<List<HotelService>>(services, HttpStatus.OK);
	}
	
}

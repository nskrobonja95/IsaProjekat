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
import edu.ftn.isa.dto.RentACarServiceDTO;
import edu.ftn.isa.dto.RoundTripSearchDTO;
import edu.ftn.isa.model.AvioCompany;
import edu.ftn.isa.model.Destination;
import edu.ftn.isa.model.Flight;
import edu.ftn.isa.model.Hotel;
import edu.ftn.isa.model.RentACarService;
import edu.ftn.isa.repositories.AvioRepository;
import edu.ftn.isa.repositories.DestinationRepository;
import edu.ftn.isa.repositories.FlightRepository;
import edu.ftn.isa.repositories.FlightReservationRepository;
import edu.ftn.isa.repositories.HotelRepository;
import edu.ftn.isa.repositories.RentACarRepository;

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
		System.out.println(hotels);
		List<HotelDTO> retVal = new ArrayList<HotelDTO>();
		
		for(Hotel hotel : hotels) {
			retVal.add(HotelDTO.parseHotel(hotel));
		}
		return new ResponseEntity<List<HotelDTO>>(retVal, HttpStatus.OK);
	}
	
	@GetMapping("/carHire")
	public ResponseEntity<?> getAllRentACar() {
		List<RentACarService> rentACars = rentACarRepo.findAll();
		System.out.println(rentACars);
		List<RentACarServiceDTO> retVal = new ArrayList<RentACarServiceDTO>();
		
		for(RentACarService rentACar : rentACars) {
			retVal.add(RentACarServiceDTO.parseRentACar(rentACar));
		}
		return new ResponseEntity<List<RentACarServiceDTO>>(retVal, HttpStatus.OK);
	}
	
	@PostMapping("/roundTripSearch")
	public ResponseEntity<?> searchFlights(@RequestBody RoundTripSearchDTO searchDto) throws ParseException {
		Date takeoff = new SimpleDateFormat("yyyy-MM-dd").parse(searchDto.getDepartDate());
		Date landing = new SimpleDateFormat("yyyy-MM-dd").parse(searchDto.getReturnDate());
		List<Flight> filteredFlights = flightRepo.roundTripSearch(takeoff, landing, destRepo.findByName(searchDto.getFrom()), destRepo.findByName(searchDto.getTo()));
		List<Flight> retVal = new ArrayList<Flight>();
		for(Flight f : filteredFlights) {
			int numOfRes = flightResRepo.countNumOfReservationsForFlight(f.getId());
			if(numOfRes < f.getNumberOfRows()) {
				int availableSeats = f.getNumberOfRows() - numOfRes;
				if(availableSeats >= searchDto.getNumOfPpl())
					retVal.add(f);
			}
		}
		return new ResponseEntity<List<Flight>>(retVal, HttpStatus.OK);
	}
	
}

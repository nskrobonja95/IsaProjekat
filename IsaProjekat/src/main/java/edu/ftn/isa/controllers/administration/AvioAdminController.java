package edu.ftn.isa.controllers.administration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

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

import edu.ftn.isa.dto.BasicAvioInfoDTO;
import edu.ftn.isa.dto.DestinationDTO;
import edu.ftn.isa.dto.DestinationsWrapper;
import edu.ftn.isa.dto.FlightDTO;
import edu.ftn.isa.dto.SeatConfigDTO;
import edu.ftn.isa.model.AvioCompany;
import edu.ftn.isa.model.Destination;
import edu.ftn.isa.model.Flight;
import edu.ftn.isa.model.FlightClass;
import edu.ftn.isa.model.FlightSeat;
import edu.ftn.isa.repositories.AvioRepository;
import edu.ftn.isa.repositories.DestinationRepository;
import edu.ftn.isa.repositories.FlightRepository;
import edu.ftn.isa.repositories.FlightSeatRepository;
import edu.ftn.isa.security.CustomUserDetails;

@RestController
@RequestMapping("/avioadmin")
public class AvioAdminController {
	
	@Autowired
	private FlightRepository flightRepo;
	
	@Autowired
	private DestinationRepository destRepo;
	
	@Autowired
	private AvioRepository avioRepo;
	
	@Autowired FlightSeatRepository flightSeatRepo;

	@PostMapping("/addFlight")
	public ResponseEntity<?> addFlight(@Valid
			@RequestBody Flight flight) {
		flightRepo.save(flight);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("/createDestination")
	public ResponseEntity<?> createDestination(@Valid
			@RequestBody Destination dest) {
		destRepo.save(dest);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("/addDestination/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseEntity<?> createDestination(@Valid
			@RequestBody Destination dest, 
			@PathVariable("id") Long id) {
		Optional<AvioCompany> optionalAvio = avioRepo.findById(id);
		if(!optionalAvio.isPresent())
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		AvioCompany avio = optionalAvio.get();
		avio.getDestinations().add(dest);
		avioRepo.save(avio);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("/addDestinations/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseEntity<?> addDestinations(
			@RequestBody DestinationsWrapper destsDto, 
			@PathVariable("id") Long id) {
		Optional<AvioCompany> optionalAvio = avioRepo.findById(id);
		if(!optionalAvio.isPresent())
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		AvioCompany avio = optionalAvio.get();
		for(int i=0; i<destsDto.getDestinations().size(); ++i) {
			avio.getDestinations().add(destRepo.findByNameAndDeleted(
					destsDto.getDestinations().get(i).getName(), false));
		}
		avioRepo.save(avio);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("/addFlight/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseEntity<?> createFlight(@Valid
			@RequestBody Flight flight,
			@PathVariable("id") Long avioId) {
		Optional<AvioCompany> avio = avioRepo.findById(avioId);
		if(!avio.isPresent())
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		flight.setAvioCompany(avio.get());
		flightRepo.save(flight);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("/createSeats/{flightId}")
	public ResponseEntity<?> createSeatsAPI(@RequestBody SeatConfigDTO configDto, 
			@PathVariable("flightId") Long flightId) {
		Flight f = flightRepo.findById(flightId).get();
		switch(configDto.getConfigType()) {
			case "SmallJet": createSeats(configDto.getNumOfRows(), 6, f); break;
			case "MediumJet": createSeats(configDto.getNumOfRows(), 7, f); break;
			case "AirBus": createSeats(configDto.getNumOfRows(), 8, f); break;
			case "JumboJet": createSeats(configDto.getNumOfRows(), 10, f); break;
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	private void createSeats(int numOfRows, int numOfCols, Flight f) {
		int seatNumber = 0;
		for(int i=1; i<=numOfRows; ++i) {
			for(int j=1; j<=numOfCols; ++j) {
				FlightSeat fs = new FlightSeat();
				fs.setAvailable(true);
				fs.setFastReservation(false);
				fs.setFlight(f);
				fs.setColNo(j);
				fs.setRowNo(i);
				fs.setSeatNumber(++seatNumber);
				if(i <= numOfRows/3)
					fs.setFlightClass(FlightClass.Business);
				else
					fs.setFlightClass(FlightClass.Economic);
				flightSeatRepo.save(fs);
			}
		}
	}

	@GetMapping("/getCompany")
	public ResponseEntity<?> getAvioCompany() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		AvioCompany retVal = avioRepo.findByAdmin(userDetails.getUser());
		return new ResponseEntity<AvioCompany>(retVal, HttpStatus.OK);
	}
	
	@PutMapping("/updateBasicCompanyInfo")
	public ResponseEntity<?> updateBasicCompanyInfo(@RequestBody BasicAvioInfoDTO avioDto) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		AvioCompany avioToModify = avioRepo.findByAdmin(userDetails.getUser());
		avioToModify.setName(avioDto.getName());
		avioToModify.setAddress(avioDto.getAddress());
		avioToModify.setPromo(avioDto.getPromo());
		avioRepo.save(avioToModify);
		return new ResponseEntity<AvioCompany>(avioToModify, HttpStatus.OK);
	}
	
	@PostMapping("/addDestination")
	public ResponseEntity<?> addDest(@RequestBody DestinationDTO destDto) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		AvioCompany avio = avioRepo.findByAdmin(userDetails.getUser());
		Destination dest = new Destination();
		dest.setName(destDto.getName());
		avio.getDestinations().add(dest);
		destRepo.save(dest);
		avioRepo.save(avio);
		return new ResponseEntity<AvioCompany>(avio, HttpStatus.OK);
	}
	
	@DeleteMapping("/removeDestination/{id}")
	public ResponseEntity<?> removeDestatination(@PathVariable("id") Long id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		AvioCompany avio = avioRepo.findByAdmin(userDetails.getUser());
		for(Destination dest : avio.getDestinations()) {
			if(dest.getId() == id) {
				avio.getDestinations().remove(dest);
				break;
			}
		}
		avioRepo.save(avio);
		return new ResponseEntity<Collection<Destination>>(avio.getDestinations(), HttpStatus.OK);
	}
	
	@GetMapping("/getFlights")
	public ResponseEntity<?> getFlights() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		AvioCompany avio = avioRepo.findByAdmin(userDetails.getUser());
		List<Flight> flights = flightRepo.findByAvioCompany(avio);
		return new ResponseEntity<List<Flight>>(flights, HttpStatus.OK);
	}
	
	@GetMapping("/getDestinations")
	public ResponseEntity<?> getDestinations() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		AvioCompany avio = avioRepo.findByAdmin(userDetails.getUser());
		List<AvioCompany> avios = new ArrayList<AvioCompany>();
		avios.add(avio);
		List<Destination> dests = destRepo.findByAvioCompanies(avios);
		return new ResponseEntity<List<Destination>>(dests, HttpStatus.OK);
	}
	
	@PostMapping("/createFlight")
	public ResponseEntity<?> createFlight(@RequestBody FlightDTO flightData) throws ParseException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		AvioCompany avio = avioRepo.findByAdmin(userDetails.getUser());
		Destination from = destRepo.findByNameAndDeleted(flightData.getFrom(), false);
		Destination to = destRepo.findByNameAndDeleted(flightData.getTo(), false);
		Date takeoff = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(flightData.getDepart());
		Date landing = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(flightData.getLand());
		Flight flight = new Flight();
		flight.setAvioCompany(avio);
		flight.setBaggageOver7Price(flightData.getPriceForBaggageOver7kg());
		flight.setBaggageOver20Price(flightData.getPriceForBaggageOver14kg());
		flight.setEconomicClassPrice(flightData.getEconomicPrice());
		flight.setBussinessClassPrice(flightData.getBusinessPrice());
		flight.setFrom(from);
		flight.setToDest(to);
		flight.setTakeoff(takeoff);
		flight.setLanding(landing);
		flight.setConfigurationType(flightData.getConfigType());
		flight.setNumOfRows(flightData.getNumOfRows());
		flightRepo.save(flight);
		return new ResponseEntity<>(HttpStatus.OK); 
	}
	
}

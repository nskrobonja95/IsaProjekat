package edu.ftn.isa.controllers.administration;

import java.text.ParseException;
import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

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

import edu.ftn.isa.dto.AvioStatisticsDTO;
import edu.ftn.isa.dto.BasicAvioInfoDTO;
import edu.ftn.isa.dto.DestinationDTO;
import edu.ftn.isa.dto.FlightDTO;
import edu.ftn.isa.model.AvioCompany;
import edu.ftn.isa.model.Destination;
import edu.ftn.isa.model.Flight;
import edu.ftn.isa.security.CustomUserDetails;
import edu.ftn.isa.services.AvioService;
import edu.ftn.isa.services.DestinationService;
import edu.ftn.isa.services.FlightService;
import edu.ftn.isa.services.StatsService;

@RestController
@RequestMapping("/avioadmin")
public class AvioAdminController {
	
	@Autowired
	private DestinationService destService;
	
	@Autowired
	private AvioService avioService;
	
	@Autowired
	private FlightService flightService;
	
	@Autowired
	private StatsService statsService;

	@PostMapping("/addFlight")
	public ResponseEntity<?> addFlight(@Valid
			@RequestBody Flight flight) {
		Flight f = flightService.saveFlight(flight);
		if(f == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
//	@PostMapping("/addDestination/{id}")
//	@Consumes(MediaType.APPLICATION_JSON)
//	public ResponseEntity<?> createDestination(@Valid
//			@RequestBody Destination dest, 
//			@PathVariable("id") Long id) {
//		Optional<AvioCompany> optionalAvio = avioRepo.findById(id);
//		if(!optionalAvio.isPresent())
//			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//		AvioCompany avio = optionalAvio.get();
//		avio.getDestinations().add(dest);
//		avioRepo.save(avio);
//		return new ResponseEntity<>(HttpStatus.OK);
//	}
	
//	@PostMapping("/addFlight/{id}")
//	@Consumes(MediaType.APPLICATION_JSON)
//	public ResponseEntity<?> createFlight(@Valid
//			@RequestBody Flight flight,
//			@PathVariable("id") Long avioId) {
//		Optional<AvioCompany> avio = avioRepo.findById(avioId);
//		if(!avio.isPresent())
//			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//		flight.setAvioCompany(avio.get());
//		flightRepo.save(flight);
//		return new ResponseEntity<>(HttpStatus.OK);
//	}

	@GetMapping("/getCompany")
	public ResponseEntity<?> getAvioCompany() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		AvioCompany retVal = avioService.getAvioByAdmin(userDetails.getUser());
		if(retVal == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<AvioCompany>(retVal, HttpStatus.OK);
	}
	
	@PutMapping("/updateBasicCompanyInfo")
	public ResponseEntity<?> updateBasicCompanyInfo(@RequestBody BasicAvioInfoDTO avioDto) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		AvioCompany modifiedAvio = avioService.updateAvio(avioDto, userDetails.getUser());
		if(modifiedAvio == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<AvioCompany>(modifiedAvio, HttpStatus.OK);
	}
	
	@PostMapping("/addDestination")
	public ResponseEntity<?> addDest(@RequestBody DestinationDTO destDto) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		AvioCompany avio = avioService.addDestinationToAvio(destDto.getName(), userDetails.getUser());
		if(avio == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<AvioCompany>(avio, HttpStatus.OK);
	}
	
	@DeleteMapping("/removeDestination/{id}")
	public ResponseEntity<?> removeDestatination(@PathVariable("id") Long id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		AvioCompany avio = avioService.removeDestinationFromAvio(id, userDetails.getUser());
		if(avio == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<Collection<Destination>>(avio.getDestinations(), HttpStatus.OK);
	}
	
	@GetMapping("/getFlights")
	public ResponseEntity<?> getFlights() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		List<Flight> flights = flightService.getFlightsOfAvio(userDetails.getUser());
		return new ResponseEntity<List<Flight>>(flights, HttpStatus.OK);
	}
	
	@GetMapping("/getDestinations")
	public ResponseEntity<?> getDestinations() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		List<Destination> dests = destService.getDestinationsForAdmin(userDetails.getUser());
		return new ResponseEntity<List<Destination>>(dests, HttpStatus.OK);
	}
	
	@GetMapping("/getRestOfDestinations")
	public ResponseEntity<?> getRestOfDestinations() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		List<Destination> dests = destService.getRestOfDestinationsForAdmin(userDetails.getUser());
		return new ResponseEntity<List<Destination>>(dests, HttpStatus.OK);
	}
	
	@PostMapping("/createFlight")
	public ResponseEntity<?> createFlight(@RequestBody FlightDTO flightData) throws ParseException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		Flight flight = flightService.createFlight(userDetails.getUser(), flightData);
		if(flight == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/avioStatistics")
	public ResponseEntity<?> avioStatistics() throws ParseException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		AvioStatisticsDTO stats = statsService.getAvioStats(userDetails.getUser());
		return new ResponseEntity<AvioStatisticsDTO>(stats, HttpStatus.OK);
	}
	
}

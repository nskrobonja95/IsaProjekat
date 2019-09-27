package edu.ftn.isa.controllers.administration;

import java.text.ParseException;
import java.util.Collection;
import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

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
import edu.ftn.isa.dto.GrossIntervalDTO;
import edu.ftn.isa.model.AvioCompany;
import edu.ftn.isa.model.Destination;
import edu.ftn.isa.model.Flight;
import edu.ftn.isa.model.Hotel;
import edu.ftn.isa.security.CustomUserDetails;
import edu.ftn.isa.services.AvioService;
import edu.ftn.isa.services.DestinationService;
import edu.ftn.isa.services.FlightService;
import edu.ftn.isa.services.StatsService;
import edu.ftn.isa.util.CustomErrorType;

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

	@GetMapping("/getCompany/{id}")
	public ResponseEntity<?> getAvioCompany(@PathVariable("id") Long id) {
		
		AvioCompany retVal = avioService.getAvioById(id);
		if(retVal == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<AvioCompany>(retVal, HttpStatus.OK);
	}
	@GetMapping("/getCompanies")
	public ResponseEntity<?> getAvioCompanies() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		List<AvioCompany> retVal = avioService.getAviosByAdmin(userDetails.getUser());
		if(retVal == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<List<AvioCompany>>(retVal, HttpStatus.OK);
	}
	
	@PutMapping("/updateBasicCompanyInfo/{id}")
	public ResponseEntity<?> updateBasicCompanyInfo(@PathVariable("id") Long id,@RequestBody BasicAvioInfoDTO avioDto) {
		AvioCompany modifiedAvio = avioService.updateAvio(avioDto, id);
		if(modifiedAvio == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<AvioCompany>(modifiedAvio, HttpStatus.OK);
	}
	
	@PostMapping("/addDestination/{avioId}")
	public ResponseEntity<?> addDest(@PathVariable("avioId") Long avioId, @RequestBody DestinationDTO destDto) {
		AvioCompany avio = avioService.addDestinationToAvio(destDto.getName(), avioId);
		if(avio == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<AvioCompany>(avio, HttpStatus.OK);
	}
	
	@DeleteMapping("/removeDestination/{destId}/{avioId}")
	public ResponseEntity<?> removeDestatination(@PathVariable("destId") Long destId, @PathVariable("avioId") Long avioId) {
		
		AvioCompany avio = avioService.removeDestinationFromAvio(destId, avioId);
		if(avio == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<Collection<Destination>>(avio.getDestinations(), HttpStatus.OK);
	}
	
	@GetMapping("/getFlights/{avioId}")
	public ResponseEntity<?> getFlights(@PathVariable("avioId") Long avioId) {
		
		List<Flight> flights = flightService.getFlightsOfAvio(avioId);
		if(flights == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<List<Flight>>(flights, HttpStatus.OK);
	}
	
	@GetMapping("/getDestinations/{avioId}")
	public ResponseEntity<?> getDestinations(@PathVariable("avioId") Long avioId) {
		List<Destination> dests = destService.getDestinationsForAdmin(avioId);
		return new ResponseEntity<List<Destination>>(dests, HttpStatus.OK);
	}
	
	@GetMapping("/getRestOfDestinations/{avioId}")
	public ResponseEntity<?> getRestOfDestinations(@PathVariable("avioId") Long avioId) {
		
		List<Destination> dests = destService.getRestOfDestinationsForAdmin(avioId);
		return new ResponseEntity<List<Destination>>(dests, HttpStatus.OK);
	}
	
	@PostMapping("/createFlight/{avioId}")
	public ResponseEntity<?> createFlight(@PathVariable("avioId") Long avioId, @RequestBody FlightDTO flightData) throws ParseException {
		
		Flight flight = flightService.createFlight(avioId, flightData);
		if(flight == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/avioStatistics/{avioId}")
	public ResponseEntity<?> avioStatistics(@PathVariable("avioId") Long avioId) throws ParseException {
		
		AvioStatisticsDTO stats = statsService.getAvioStats(avioId);
		if(stats == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<AvioStatisticsDTO>(stats, HttpStatus.OK);
	}
	@PostMapping("/getGrossForInterval/{avioId}")
	public ResponseEntity<?> getGrossForInterval(@PathVariable("avioId") Long avioId, @RequestBody GrossIntervalDTO grossObj) throws ParseException{
		int grossResult = statsService.getGrossForInterval(avioId, grossObj);
		if(grossResult == 1) return new ResponseEntity<> (HttpStatus.BAD_REQUEST);
		if(grossResult == 2) return new ResponseEntity<>(new CustomErrorType("No income for given period."), HttpStatus.NOT_FOUND);
		return new ResponseEntity<Integer>(grossResult, HttpStatus.OK);
		
	}
	
}

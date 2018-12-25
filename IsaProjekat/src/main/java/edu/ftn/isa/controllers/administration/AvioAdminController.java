package edu.ftn.isa.controllers.administration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.ftn.isa.dto.DestinationDTO;
import edu.ftn.isa.dto.DestinationsWrapper;
import edu.ftn.isa.model.AvioCompany;
import edu.ftn.isa.model.Destination;
import edu.ftn.isa.model.Flight;
import edu.ftn.isa.repositories.AvioRepository;
import edu.ftn.isa.repositories.DestinationRepository;
import edu.ftn.isa.repositories.FlightRepository;

@RestController
@RequestMapping("/avioadmin")
public class AvioAdminController {
	
	@Autowired
	private FlightRepository flightRepo;
	
	@Autowired
	private DestinationRepository destRepo;
	
	@Autowired
	private AvioRepository avioRepo;

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
			avio.getDestinations().add(destRepo.findByName(
					destsDto.getDestinations().get(i).getName()));
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
	
}

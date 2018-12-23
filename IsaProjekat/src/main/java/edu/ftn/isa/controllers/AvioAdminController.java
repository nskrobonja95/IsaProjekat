package edu.ftn.isa.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	/*
	 * postoji mogucnost da se salje id od destinacije
	 */
	@PostMapping("/addDestination/{id}")
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
	
}

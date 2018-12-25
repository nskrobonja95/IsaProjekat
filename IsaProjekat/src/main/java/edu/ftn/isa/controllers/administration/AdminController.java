package edu.ftn.isa.controllers.administration;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.ftn.isa.model.AvioCompany;
import edu.ftn.isa.model.Hotel;
import edu.ftn.isa.model.RentACarService;
import edu.ftn.isa.repositories.AvioRepository;
import edu.ftn.isa.repositories.HotelRepository;
import edu.ftn.isa.repositories.RentACarRepository;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AvioRepository avioRepo;
	
	@Autowired
	private HotelRepository hotelRepo;
	
	@Autowired
	private RentACarRepository rentACarRepo;
	
	@PostMapping("/addAvio")
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseEntity<?> addAvio(@RequestBody AvioCompany aviocomp) {
		avioRepo.save(aviocomp);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PostMapping("/addHotel")
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseEntity<?> addHotel(@Valid @RequestBody Hotel hotel) {
		hotelRepo.save(hotel);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PostMapping("/addRentACarService")
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseEntity<?> addRentACar(
			@Valid @RequestBody RentACarService rentACar) {
		rentACarRepo.save(rentACar);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
}

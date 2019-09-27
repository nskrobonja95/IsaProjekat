package edu.ftn.isa.controllers.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.ftn.isa.model.AvioCompany;
import edu.ftn.isa.model.Destination;
import edu.ftn.isa.repositories.AvioRepository;
import edu.ftn.isa.repositories.DestinationRepository;

@RequestMapping("/avio")
@RestController
public class AvioController {

	@Autowired
	private AvioRepository avioRepo;
	
	@Autowired
	private DestinationRepository destRepo;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getAvio(
			@PathVariable("id") Long id) {
		Optional<AvioCompany> optionalAvio = avioRepo.findById(id);
		if(!optionalAvio.isPresent())
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<AvioCompany>(optionalAvio.get(), HttpStatus.OK);
	}
	
	@GetMapping("/destinations/{id}")
	public ResponseEntity<?> getDestinations(
			@PathVariable("id") Long id) {
		//Authentication auth = SecurityContextHolder.getContext().getAuthentication()
		Optional<AvioCompany> optionalAvio = avioRepo.findById(id);
		if(!optionalAvio.isPresent())
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		List<AvioCompany> avioCompanies = new ArrayList<AvioCompany>();
		avioCompanies.add(optionalAvio.get());
		List<Destination> dests = destRepo.findByAvioCompanies(avioCompanies);
		return new ResponseEntity<List<Destination>>(dests, HttpStatus.OK);
	}
	
}
	

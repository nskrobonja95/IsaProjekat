package edu.ftn.isa.controllers.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.ftn.isa.dto.AvioCompanyDTO;
import edu.ftn.isa.dto.DestinationDTO;
import edu.ftn.isa.model.AvioCompany;
import edu.ftn.isa.model.Destination;
import edu.ftn.isa.repositories.AvioRepository;

@RestController
@RequestMapping("/app")
public class UnregisteredUsersController {

	@Autowired
	private AvioRepository avioRepo;
	
	@GetMapping("/airlines")
	public ResponseEntity<?> getAllAirlines() {
		List<AvioCompany> avios = avioRepo.findAll();
		List<AvioCompanyDTO> retVal = new ArrayList<AvioCompanyDTO>();
		AvioCompanyDTO temp;
		for(AvioCompany avio : avios) {
			temp = new AvioCompanyDTO();
			temp.setAddress(avio.getAddress());
			temp.setName(avio.getName());
			temp.setPromo(avio.getPromo());
			temp.setAdmin(avio.getAdmin());
			retVal.add(temp);
		}
		return new ResponseEntity<List<AvioCompanyDTO>>(retVal, HttpStatus.OK);
	}
	
	@GetMapping("/getAllDestinations/{id}")
	public ResponseEntity<?> getDestinationsOfAvioCompany(
			@PathVariable("id") Long avioId) {
		Optional<AvioCompany> avio = avioRepo.findById(avioId);
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
	
	@GetMapping("/getAllDestinations/{name}")
	public ResponseEntity<?> getDestinationsOfAvioCompanyByName(
			@PathVariable("id") String avioName) {
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
	
}

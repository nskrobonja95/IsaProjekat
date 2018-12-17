package edu.ftn.isa.controllers;

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
import edu.ftn.isa.repositories.AvioRepository;

@RestController
@RequestMapping("api/admin")
public class AdminController {

	@Autowired
	private AvioRepository avioRepo;
	
	@PostMapping("/addAvio")
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseEntity<?> addAvio(@RequestBody AvioCompany aviocomp) {
		avioRepo.save(aviocomp);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
}

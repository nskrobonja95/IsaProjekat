package edu.ftn.isa.controllers;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.ftn.isa.model.Role;
import edu.ftn.isa.model.User;
import edu.ftn.isa.payload.LoginPayload;
import edu.ftn.isa.payload.SignupPayload;
import edu.ftn.isa.repositories.UserRepository;

@RestController
@RequestMapping("api/auth")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseEntity<?> login(@RequestBody LoginPayload loginPayload) {
		Authentication auth = authManager.authenticate
				(new UsernamePasswordAuthenticationToken(
						loginPayload.getUsername(), loginPayload.getPassword()));
		if(!auth.isAuthenticated()) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		User u = userRepo.findByUsername(loginPayload.getUsername());
		return new ResponseEntity<User>(u, HttpStatus.OK);
	}
	
	@PostMapping("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseEntity<?> login(@RequestBody SignupPayload signupPayload) {
		if(userRepo.findByUsername(signupPayload.getUsername()) != null) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		if(userRepo.findByEmail(signupPayload.getEmail()) != null) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		User u = createUser(signupPayload);
		userRepo.save(u);
		return new ResponseEntity<User>(HttpStatus.CREATED);
	}
	
	private User createUser(SignupPayload payload) {
		User u = new User();
		u.setName(payload.getName());
		u.setLastname(payload.getLastname());
		u.setEmail(payload.getEmail());
		u.setPassword(passwordEncoder.encode(payload.getPassword()));
		u.setUsername(payload.getUsername());
		u.setState(payload.getState());
		u.setEnabled(true);
		u.setRole(Role.User);
		return u;
	}
	
}

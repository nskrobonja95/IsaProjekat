package edu.ftn.isa.controllers.app;

import java.util.UUID;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.ftn.isa.dto.UserDTO;
import edu.ftn.isa.model.Role;
import edu.ftn.isa.model.User;
import edu.ftn.isa.payload.LoginPayload;
import edu.ftn.isa.payload.SignupPayload;
import edu.ftn.isa.repositories.UserRepository;
import edu.ftn.isa.services.EmailService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private EmailService emailService;
	
	@PostMapping("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseEntity<?> login(@Valid @RequestBody LoginPayload loginPayload) {
		try {
			Authentication auth = authManager.authenticate
					(new UsernamePasswordAuthenticationToken(
							loginPayload.getUsername(), loginPayload.getPassword()));
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		User u = userRepo.findByUsername(loginPayload.getUsername());
		UserDTO userDTO = UserDTO.parseUsertoDTO(u);
		return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
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
		try{
			userRepo.save(u);
			u.setEnabled(false);
			SimpleMailMessage registrationEmail = new SimpleMailMessage();
			registrationEmail.setTo(u.getEmail());
			registrationEmail.setSubject("Flight App - Confirmation Email");
			String url = "http://localhost:8080/auth/confirmRegistration/" + u.getVerificationToken();
			String verificationEmail = "Hello %s, \nTo confirm your e-mail address for Flight Application, please click the link below:\n %s";
			String email = String.format(verificationEmail, u.getName(), url);
			registrationEmail.setText(email);
			registrationEmail.setFrom("noreply@domain.com");
			
			emailService.sendEmail(registrationEmail);
		} catch(Exception e) {
			return new ResponseEntity<User>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
		return new ResponseEntity<User>(HttpStatus.CREATED);
	}
	
	@GetMapping("/confirmRegistration/{token}")
	public ResponseEntity<?> confirmUser(@PathVariable("token") String token){
		
		User user = userRepo.findByVerificationToken(token);
		
		if(user != null){	
			user.setEnabled(true);
			userRepo.save(user);
			
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	private User createUser(SignupPayload payload) {
		User u = new User();
		u.setName(payload.getName());
		u.setLastname(payload.getLastname());
		u.setEmail(payload.getEmail());
		u.setPassword(passwordEncoder.encode(payload.getPassword()));
		u.setUsername(payload.getUsername());
		u.setCity(payload.getCity());
		u.setEnabled(false);
		u.setVerificationToken(UUID.randomUUID().toString());
		u.setRole(Role.User);
		return u;
	}
	
}

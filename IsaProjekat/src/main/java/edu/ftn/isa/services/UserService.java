package edu.ftn.isa.services;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import edu.ftn.isa.dto.UserDTO;
import edu.ftn.isa.model.Role;
import edu.ftn.isa.model.User;
import edu.ftn.isa.payload.SignupPayload;
import edu.ftn.isa.repositories.FlightSeatRepository;
import edu.ftn.isa.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private EmailService emailService;

	@Transactional
	public User findByUsername(String username) {
		User u = userRepo.findByUsername(username);
		return u;
	}

	public boolean register(SignupPayload signupPayload) {
		User user = userRepo.findByUsername(signupPayload.getUsername());
		if(user != null) {
			return false;
		}
		if(userRepo.findByEmail(signupPayload.getEmail()) != null) {
			return false;
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
			return false;
		}
		return true;
	}
	
	private User createUser(SignupPayload payload) {
		User u = new User();
		u.setName(payload.getName());
		u.setLastname(payload.getLastname());
		u.setEmail(payload.getEmail());
		u.setPassword(passwordEncoder.encode(payload.getPassword()));
		u.setUsername(payload.getUsername());
		u.setCity(payload.getCity());
		u.setPhoneNumber(payload.getPhoneNumber());
		u.setEnabled(false);
		u.setVerificationToken(UUID.randomUUID().toString());
		u.setRole(Role.User);
		return u;
	}

	public boolean editUser(UserDTO userdto, String oldusername, String username) {
		if(!username.equals(oldusername))
			return false;
		User user = userRepo.findByUsername(oldusername);
		if(!oldusername.equals(userdto.getUsername())) {
			if(userRepo.findByUsername(userdto.getUsername()) != null)
				return false;
		}
		if(!user.getEmail().equals(userdto.getEmail())) {
			if(userRepo.findByEmail(userdto.getEmail()) != null)
				return false;
		}
		
		user.setName(userdto.getName());
		user.setLastname(userdto.getLastname());
		user.setEmail(userdto.getEmail());
		user.setPhoneNumber(userdto.getPhoneNumber());
		user.setUsername(userdto.getUsername());
		user.setCity(userdto.getCity());
		try {
			userRepo.save(user);
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	
}

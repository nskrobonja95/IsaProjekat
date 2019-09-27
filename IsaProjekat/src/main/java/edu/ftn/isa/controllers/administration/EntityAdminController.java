package edu.ftn.isa.controllers.administration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.ftn.isa.model.User;
import edu.ftn.isa.repositories.UserRepository;
import edu.ftn.isa.security.CustomUserDetails;

@RestController
@RequestMapping("/entityadmin")
public class EntityAdminController {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PutMapping("/changePassword/{newpassword}")
	public ResponseEntity<?> updateBasicHotelInfo(@PathVariable("newpassword") String newpassword) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		User user = userDetails.getUser();
		user.setPassword(passwordEncoder.encode(newpassword));
		user.setPasswordChanged(true);
		userRepo.save(user);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}

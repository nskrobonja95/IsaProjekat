package edu.ftn.isa.controllers.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.ftn.isa.dto.UserDTO;
import edu.ftn.isa.model.User;
import edu.ftn.isa.payload.PasswordChangePayload;
import edu.ftn.isa.repositories.UserRepository;

@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository userRepo;
	
	@PutMapping("/edit/{oldusername}")
	public ResponseEntity<?> editUser(
			@PathVariable("oldusername") String oldusername, 
			@RequestBody UserDTO userdto) {
		User user = userRepo.findByUsername(oldusername);
		if(!oldusername.equals(userdto.getUsername())) {
			if(user != null)
				return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		user.setName(userdto.getName());
		user.setLastname(userdto.getLastname());
		user.setUsername(userdto.getUsername());
		user.setState(userdto.getState());
		try {
			userRepo.save(user);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("/changePassword/{username}")
	public ResponseEntity<?> changePassword(
			@PathVariable("username") String username, 
			@RequestBody PasswordChangePayload payload) {
		
		User user = userRepo.findByUsername(username);
		if(user == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(payload.getNewPassword()));
		try {
			userRepo.save(user);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}

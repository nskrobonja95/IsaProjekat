package edu.ftn.isa.controllers.administration;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.ftn.isa.dto.AdminDTO;
import edu.ftn.isa.model.AvioCompany;
import edu.ftn.isa.model.Hotel;
import edu.ftn.isa.model.RentACarService;
import edu.ftn.isa.model.Role;
import edu.ftn.isa.model.User;
import edu.ftn.isa.repositories.AvioRepository;
import edu.ftn.isa.repositories.HotelRepository;
import edu.ftn.isa.repositories.RentACarRepository;
import edu.ftn.isa.repositories.UserRepository;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AvioRepository avioRepo;
	
	@Autowired
	private HotelRepository hotelRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RentACarRepository rentACarRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
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
	
	@PutMapping("/changeRole")
	public ResponseEntity<?> changeRole(
			@QueryParam("username") String username,
			@QueryParam("newrole") String newrole) {
		User user = userRepo.findByUsername(username);
		Role role;
		if((role = getRole(newrole)) == null || user == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		user.setRole(role);
		userRepo.save(user);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	private Role getRole(String role) {
		switch(role) {
			case "AvioAdmin": return Role.AvioAdmin;
			case "SysAdmin": return Role.SysAdmin;
			case "RentACarAdmin": return Role.RentACarAdmin;
			case "HotelAdmin": return Role.HotelAdmin;
			case "User": return Role.User;
			default: return null;
		}
	}
	
	@DeleteMapping("/deleteUser/{username}")
	public ResponseEntity<?> deleteUser(
			@PathVariable("username") String username) {
		User user = userRepo.findByUsername(username);
		if(user == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		try{
			userRepo.delete(user);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/getUser/{username}")
	public ResponseEntity<?> getUser(
			@PathVariable("username") String username) {
		User user = userRepo.findByUsername(username);
		if(user == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@PutMapping("/setAvioAdmin")
	public ResponseEntity<?> setAvioAdmin(
			@QueryParam("username") String username, 
			@QueryParam("avioid") Long avioId) {
		User user = userRepo.findByUsername(username);
		if(user == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		Optional<AvioCompany> avioOp = avioRepo.findById(avioId);
		if(!avioOp.isPresent())
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		AvioCompany avio = avioOp.get();
		avio.setAdmin(user);
		avioRepo.save(avio);
		return new ResponseEntity<User>(HttpStatus.OK);
	}
	
	@PutMapping("/setHotelAdmin")
	public ResponseEntity<?> setHotelAdmin(
			@QueryParam("username") String username, 
			@QueryParam("hotelid") Long hotelId) {
		User user = userRepo.findByUsername(username);
		if(user == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		Optional<Hotel> hotelOp = hotelRepo.findById(hotelId);
		if(!hotelOp.isPresent())
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		Hotel hotel = hotelOp.get();
		hotel.setAdmin(user);
		hotelRepo.save(hotel);
		return new ResponseEntity<User>(HttpStatus.OK);
	}
	
	@PostMapping("/setAvioAdmin/{avioId}")
	public ResponseEntity<?> setAvioAdminForm(
			@RequestBody AdminDTO adminDto, 
			@PathVariable("avioId") Long avioId) {
		
		if(userRepo.findByUsernameOrEmail(adminDto.getUsername(), adminDto.getEmail()) != null) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		
		User user = new User();
		user.setEmail(adminDto.getEmail());
		user.setUsername(adminDto.getUsername());
		user.setPassword(passwordEncoder.encode(adminDto.getPassword()));
		user.setPasswordChanged(false);
		user.setRole(Role.AvioAdmin);
		userRepo.save(user);
		
		AvioCompany avio = avioRepo.findById(avioId).get();
		avio.setAdmin(user);
		avioRepo.save(avio);
		
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	@PostMapping("/setHotelAdmin/{hotelId}")
	public ResponseEntity<?> setHotelAdminForm(
			@RequestBody AdminDTO adminDto, 
			@PathVariable("hotelId") Long hotelId) {
		
		if(userRepo.findByUsernameOrEmail(adminDto.getUsername(), adminDto.getEmail()) != null) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		
		User user = new User();
		user.setEmail(adminDto.getEmail());
		user.setUsername(adminDto.getUsername());
		user.setPassword(passwordEncoder.encode(adminDto.getPassword()));
		user.setPasswordChanged(false);
		user.setRole(Role.HotelAdmin);
		userRepo.save(user);
		
		Hotel hotel = hotelRepo.findById(hotelId).get();
		hotel.setAdmin(user);
		hotelRepo.save(hotel);
		
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	@PostMapping("/setRASAdmin/{RASId}")
	public ResponseEntity<?> setRASAdminForm(
			@RequestBody AdminDTO adminDto, 
			@PathVariable("RASId") Long RASId) {
		
		if(userRepo.findByUsernameOrEmail(adminDto.getUsername(), adminDto.getEmail()) != null) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		
		User user = new User();
		user.setEmail(adminDto.getEmail());
		user.setUsername(adminDto.getUsername());
		user.setPassword(passwordEncoder.encode(adminDto.getPassword()));
		user.setPasswordChanged(false);
		user.setRole(Role.RentACarAdmin);
		userRepo.save(user);
		
		RentACarService ras = rentACarRepo.findById(RASId).get();
		ras.setAdmin(user);
		rentACarRepo.save(ras);
		
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	@PutMapping("/setRASAdmin")
	public ResponseEntity<?> setRASAdmin(
			@QueryParam("username") String username, 
			@QueryParam("rasid") Long rasId) {
		User user = userRepo.findByUsername(username);
		if(user == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		Optional<RentACarService> rasOp = rentACarRepo.findById(rasId);
		if(!rasOp.isPresent())
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		RentACarService ras = rasOp.get();
		ras.setAdmin(user);
		rentACarRepo.save(ras);
		return new ResponseEntity<User>(HttpStatus.OK);
	}
	
	@GetMapping("/getUsers")
	public ResponseEntity<?> getAllUsers() {
		return new ResponseEntity<List<User>>(userRepo.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/getAvioAdminUsers")
	public ResponseEntity<?> getAllAvioAdminUsers() {
		return new ResponseEntity<List<User>>(userRepo.findByRole(Role.AvioAdmin), HttpStatus.OK);
	}
	
	@GetMapping("/getHotelAdminUsers")
	public ResponseEntity<?> getAllHotelAdminUsers() {
		return new ResponseEntity<List<User>>(userRepo.findByRole(Role.HotelAdmin), HttpStatus.OK);
	}
	
	@GetMapping("/getRASAdminUsers")
	public ResponseEntity<?> getAllRASAdminUsers() {
		return new ResponseEntity<List<User>>(userRepo.findByRole(Role.RentACarAdmin), HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteAvio/{id}")
	public ResponseEntity<?> deleteAvio(
			@PathVariable("id") Long avioId) {
		AvioCompany avio = avioRepo.findById(avioId).get();
		avioRepo.delete(avio);
		return new ResponseEntity<User>(HttpStatus.OK);
	}
	
}

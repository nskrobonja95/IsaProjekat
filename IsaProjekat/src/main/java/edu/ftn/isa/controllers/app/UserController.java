package edu.ftn.isa.controllers.app;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.ftn.isa.dto.FlightReservationDTO;
import edu.ftn.isa.dto.FriendsDTO;
import edu.ftn.isa.dto.ReservationsDTO;
import edu.ftn.isa.dto.UserDTO;
import edu.ftn.isa.model.FlightReservation;
import edu.ftn.isa.model.Friends;
import edu.ftn.isa.model.HotelReservation;
import edu.ftn.isa.model.User;
import edu.ftn.isa.payload.PasswordChangePayload;
import edu.ftn.isa.repositories.FlightRepository;
import edu.ftn.isa.repositories.FlightReservationRepository;
import edu.ftn.isa.repositories.HotelReservationRepository;
import edu.ftn.isa.repositories.UserRepository;
import edu.ftn.isa.security.CustomUserDetails;
import edu.ftn.isa.services.AuthService;

@RequestMapping("/user")
@RestController
@Transactional
public class UserController {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private FlightReservationRepository flightResRepo;
	
	@Autowired
	private FlightRepository flightRepo;
	
	@Autowired
	private HotelReservationRepository hotelResRepo;
	
	@PutMapping("/edit/{oldusername}")
	public ResponseEntity<?> editUser(
			@PathVariable("oldusername") String oldusername, 
			@RequestBody UserDTO userdto, HttpServletRequest request) {
		String username = authService.extractAuthUsernameFromRequest(request);
		if(!username.equals(oldusername))
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		User user = userRepo.findByUsername(oldusername);
		if(!oldusername.equals(userdto.getUsername())) {
			if(userRepo.findByUsername(userdto.getUsername()) != null)
				return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		user.setName(userdto.getName());
		user.setLastname(userdto.getLastname());
		user.setUsername(userdto.getUsername());
		user.setCity(userdto.getCity());
		try {
			userRepo.save(user);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@PutMapping("/changePassword/{username}")
	public ResponseEntity<?> changePassword(
			@PathVariable("username") String username, 
			@RequestBody PasswordChangePayload payload,
			HttpServletRequest request) {
		String authusername = authService.extractAuthUsernameFromRequest(request);
		if(!username.equals(authusername))
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
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
	
	@GetMapping("/get")
	public ResponseEntity<?> getUserData(
			HttpServletRequest request) {
		String username = authService.extractAuthUsernameFromRequest(request);
		User user = userRepo.findByUsername(username);
		if(user == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		List<FriendsDTO> friendsDTO = new ArrayList<>();
		
		for(Friends friends : user.getPersons()){
			if(friends.getFriendshipDate()){
				friendsDTO.add(new FriendsDTO(friends.getFriends().getUsername(), friends.getFriends().getId(), friends.getFriendshipDate(),
						"accepted"));
			}else{
				friendsDTO.add(new FriendsDTO(friends.getFriends().getUsername(), friends.getFriends().getId(), friends.getFriendshipDate(),
						"requested"));
			}
				
		}
		for(Friends friends : user.getFriends()){
			if(friends.getFriendshipDate()){
				friendsDTO.add(new FriendsDTO(friends.getPersons().getUsername() ,friends.getPersons().getId(),friends.getFriendshipDate(),
						"accepted"));
			}else{
				friendsDTO.add(new FriendsDTO(friends.getPersons().getUsername() ,friends.getPersons().getId(),friends.getFriendshipDate(),
						"received"));
			}
				
		}
		
		UserDTO userDTO = UserDTO.parseUsertoDTO(user);
		userDTO.setFriends(friendsDTO);
		return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
	}
	@RequestMapping(value = "/searchFriends/{searchValue}", method = RequestMethod.GET)
	public ResponseEntity<?> getSearchFriends(@PathVariable("searchValue") String searchValue) {
		List<User> users = userRepo.querySearch(searchValue);
		if (users.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		List<UserDTO> usersDTO = new ArrayList<>();
		
		//System.out.println(users.getUsername());
		for(User user : users){
			if(user.isEnabled())
			usersDTO.add(UserDTO.parseUsertoDTO(user));
		}
		System.out.println("Ovo je search"+usersDTO);
		return new ResponseEntity<List<UserDTO>>(usersDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/flightReservations", method = RequestMethod.GET)
	public ResponseEntity<?> getReservationsOfUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		List<FlightReservationDTO> retVal = new ArrayList<FlightReservationDTO>();
//		List<FlightReservation> fReservations = flightResRepo.findByUser(userDetails.getUser());
//		List<FlightReservationDTO> retVal = new ArrayList<FlightReservationDTO>();
//		for(FlightReservation res : fReservations) {
//			retVal.add(transformFromModel(res));
//		}
		
		return new ResponseEntity<List<FlightReservationDTO>>(retVal, HttpStatus.OK);
		
	}
	
	public FlightReservationDTO transformFromModel(FlightReservation fr) {
		FlightReservationDTO retVal = new FlightReservationDTO();
		retVal.setLastname(fr.getLastname());
		retVal.setName(fr.getName());
		retVal.setFlight(flightRepo.findById(retVal.getFlightId()).get());
		return retVal;
	}
	
}

package edu.ftn.isa.controllers.app;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.hibernate.StaleObjectStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.ftn.isa.dto.FlightRatingDTO;
import edu.ftn.isa.dto.FlightReservationDTO;
import edu.ftn.isa.dto.FlightReservationResponseDTO;
import edu.ftn.isa.dto.FriendsDTO;
import edu.ftn.isa.dto.HotelReservationDTO;
import edu.ftn.isa.dto.InviteFriendFlightReservationDTO;
import edu.ftn.isa.dto.SeatRowDTO;
import edu.ftn.isa.dto.UserDTO;
import edu.ftn.isa.dto.UserFlightReservationDTO;
import edu.ftn.isa.dto.UserHotelReservationDTO;
import edu.ftn.isa.model.Flight;
import edu.ftn.isa.model.FlightClass;
import edu.ftn.isa.model.FlightReservation;
import edu.ftn.isa.model.FlightSeat;
import edu.ftn.isa.model.Friends;
import edu.ftn.isa.model.HotelReservation;
import edu.ftn.isa.model.HotelServiceModel;
import edu.ftn.isa.model.ReservationStatus;
import edu.ftn.isa.model.User;
import edu.ftn.isa.payload.PasswordChangePayload;
import edu.ftn.isa.repositories.FlightRepository;
import edu.ftn.isa.repositories.FlightReservationRepository;
import edu.ftn.isa.repositories.FlightSeatRepository;
import edu.ftn.isa.repositories.HotelReservationRepository;
import edu.ftn.isa.repositories.UserRepository;
import edu.ftn.isa.security.CustomUserDetails;
import edu.ftn.isa.services.AuthService;
import edu.ftn.isa.services.EmailService;
import edu.ftn.isa.services.ReservationService;
import edu.ftn.isa.services.UserService;

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
	
	@Autowired
	private FlightSeatRepository flightSeatRepo;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private ReservationService resService;
	
	@Autowired
	private UserService userService;
	
	@PutMapping("/edit/{oldusername}")
	public ResponseEntity<?> editUser(
			@PathVariable("oldusername") String oldusername, 
			@RequestBody UserDTO userdto, HttpServletRequest request) {
		String username = authService.extractAuthUsernameFromRequest(request);
		boolean flag = userService.editUser(userdto, oldusername, username);
//		if(!username.equals(oldusername))
//			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
//		User user = userRepo.findByUsername(oldusername);
//		if(!oldusername.equals(userdto.getUsername())) {
//			if(userRepo.findByUsername(userdto.getUsername()) != null)
//				return new ResponseEntity<>(HttpStatus.CONFLICT);
//		}
//		if(!user.getEmail().equals(userdto.getEmail())) {
//			if(userRepo.findByEmail(userdto.getEmail()) != null)
//				return new ResponseEntity<>(HttpStatus.CONFLICT);
//		}
//		
//		user.setName(userdto.getName());
//		user.setLastname(userdto.getLastname());
//		user.setEmail(userdto.getEmail());
//		user.setPhoneNumber(userdto.getPhoneNumber());
//		user.setUsername(userdto.getUsername());
//		user.setCity(userdto.getCity());
//		try {
//			userRepo.save(user);
//		} catch(Exception e) {
//			e.printStackTrace();
//			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
//		}
		if(!flag) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<UserDTO>(userdto, HttpStatus.OK);
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
	
	
	@GetMapping("/getFlight/{flightId}")
	public ResponseEntity<?> loadFlight(@PathVariable("flightId") Long flightId) {
		Optional<Flight> optionalFlight = flightRepo.findById(flightId);
		if(optionalFlight.isPresent())
			return new ResponseEntity<Flight>(optionalFlight.get(), HttpStatus.OK);
		else
			return new ResponseEntity<Long>(-1L, HttpStatus.OK);
	}
	
	@GetMapping("/getSeats/{flightId}")
	public ResponseEntity<?> loadSeats(@PathVariable("flightId") Long flightId) {
//		Optional<Flight> optionalFlight = flightRepo.findById(flightId);
//		if(!optionalFlight.isPresent()) {
//			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//		}
//		Flight f = optionalFlight.get();
//		List<FlightSeat> seats = flightSeatRepo.findByFlight(optionalFlight.get());
//		int numOfCols = seats.size()/f.getNumOfRows();
//		List<SeatRowDTO> seatRows = new ArrayList<SeatRowDTO>();
//		SeatRowDTO seatRow = new SeatRowDTO();
//		for(int j=0; j<seats.size(); ++j) {
//			SeatDTO seatDto = new SeatDTO();
//			seatDto.setId(seats.get(j).getId());
//			seatDto.setColNum(seats.get(j).getColNo());
//			seatDto.setFastRes(seats.get(j).isFastReservation());
//			seatDto.setFlightClass(seats.get(j).getFlightClass().toString());
//			seatDto.setRowNum(seats.get(j).getRowNo());
//			seatDto.setSeatNumber(seats.get(j).getSeatNumber());
//			seatDto.setAvailable(seats.get(j).isAvailable());
//			seatDto.setReserved(false);
//			seatDto.setFlight(f);
//			seatRow.getSeats().add(seatDto);
//			if(seats.get(j).getSeatNumber()%numOfCols == 0) {
//				seatRows.add(seatRow);
//				seatRow = new SeatRowDTO();
//			}
//		}
		List<SeatRowDTO> seatRows = resService.loadSeats(flightId);
		return new ResponseEntity<List<SeatRowDTO>>(seatRows, HttpStatus.OK);
	}
	
	@PostMapping("/reserveRoom")
	public ResponseEntity<?> reserveRoom(@RequestBody HotelReservationDTO reservationDto) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

		int res = resService.reserveRoom(reservationDto, userDetails.getUser());
		if(res == 0) {

			return new ResponseEntity<Long>(HttpStatus.BAD_REQUEST);
		} else if(res == 1) {
			return new ResponseEntity<Long>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Long>(HttpStatus.OK);
		
	}
	
	@PostMapping("/reserve")
	public ResponseEntity<?> reserve(@RequestBody List<FlightReservationDTO> flightDto) throws ParseException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		User user = userDetails.getUser();
		FlightReservationResponseDTO dto;
		try {
			dto = resService.reserveFlightSeat(flightDto, user);
			System.out.println("caooo");
		} catch(StaleObjectStateException exp) {
			exp.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		if(!dto.isSuccesfullyReserved())
			return new ResponseEntity<FlightReservationResponseDTO>(dto, HttpStatus.BAD_REQUEST);
		return new ResponseEntity<FlightReservationResponseDTO>(dto, HttpStatus.OK);
	}
	
	@PostMapping("/sendInvitation")
	public ResponseEntity<?> sendInvitation(@RequestBody InviteFriendFlightReservationDTO reservationDto) throws ParseException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		User user = userDetails.getUser();
		User userRecipient = userRepo.findByUsername(reservationDto.getUsername());
		SimpleMailMessage registrationEmail = new SimpleMailMessage();
		registrationEmail.setTo(userRecipient.getEmail());
		registrationEmail.setSubject("Flight App - Flight Invitation");
		String confirm = "http://localhost:8080/app/acceptInvitation/";
		String refuse = "http://localhost:8080/app/refuseInvitation/";
		String flightInfo = "";
		List<FlightSeat> seats = new ArrayList<FlightSeat>();
		for(int i=0; i<reservationDto.getSeats().size(); ++i) {
			confirm += reservationDto.getSeats().get(i).getId() + "/";
			refuse += reservationDto.getSeats().get(i).getId() + "/";
			flightInfo += "Flight" + i+1 + "Seat number " + 
					reservationDto.getSeats().get(i).getSeatNumber() + " from " + 
					reservationDto.getSeats().get(i).getFlight().getFrom().getName()
					+ " to " + 
					reservationDto.getSeats().get(i).getFlight().getToDest().getName() + 
					" with " + 
					reservationDto.getSeats().get(i).getFlight().getAvioCompany().getName() +
					"\n\n";
			FlightSeat seat = flightSeatRepo.findById(reservationDto.getSeats().get(i).getId()).get();
			seat.setAvailable(false);
			seats.add(seat);
		}
		FlightReservation res = new FlightReservation();
		res.setFlightReservationSeats(seats);
		res.setName(reservationDto.getName());
		res.setLastname(reservationDto.getLastname());
		res.setPassportNumber(reservationDto.getPassportNumber());
		res.setStatus(ReservationStatus.PENDING);
		res.setUser(userRecipient);
		res.setFastReservation(false);
		res.setRate(0);
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date todayDate = new Date();
		Date reserveDate = formatter.parse(formatter.format(todayDate));
		res.setReserveDate(reserveDate);
		flightResRepo.save(res);
		if(reservationDto.getSeats().size() == 1) {
			confirm += "0";
			refuse += "0";
		}
		String invitationEmail = "Hello %s, \n\nYou have received an invitation from %s "
				+ "for a flight reservation using info:\n\tName: %s\n\tLastname: %s"
				+ "\n\tPassport: %s\n\n"
				+ "Flight(s) info:\n" + flightInfo + "\n\n"
				+ "Confirm: %s\nRefuse: %s\n"
				+ "";
		String email = String.format(invitationEmail, userRecipient.getName(), user.getName(), reservationDto.getName(), reservationDto.getLastname(), reservationDto.getPassportNumber(), confirm, refuse);
		registrationEmail.setText(email);
		registrationEmail.setFrom("noreply@domain.com");
		
		emailService.sendEmail(registrationEmail);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("/makeFastReservation/{seatId}")
	public ResponseEntity<?> makeFastReservation(@PathVariable("seatId") Long seatId) throws ParseException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		User user = userDetails.getUser();
		Optional<FlightSeat> optionalSeat = flightSeatRepo.findById(seatId);
		if(!optionalSeat.isPresent())
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		FlightSeat seat = optionalSeat.get();
		FlightReservation res = new FlightReservation();
		res.setFastReservation(true);
		seat.setAvailable(false);
		List<FlightSeat> seats = new ArrayList<FlightSeat>();
		seats.add(seat);
		res.setFlightReservationSeats(seats);
		res.setName(user.getName());
		res.setLastname(user.getLastname());
		res.setPassportNumber("090909");
		if(seat.getFlightClass().equals(FlightClass.Business))
			res.setPrice(seat.getFlight().getBussinessClassPrice());
		else
			res.setPrice(seat.getFlight().getEconomicClassPrice());
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date todayDate = new Date();
		Date reserveDate = formatter.parse(formatter.format(todayDate));
		res.setReserveDate(reserveDate);
		res.setUser(user);
		res.setStatus(ReservationStatus.APPROVED);
		flightResRepo.save(res);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/flightUserReservationsList")
	public ResponseEntity<?> getUserFlightReservations(){
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
		User user = userDetails.getUser();
		
		List<FlightReservation> reservationsList = flightResRepo.getUserReservationsList(user.getId());
		List<UserFlightReservationDTO> retVal = new ArrayList<UserFlightReservationDTO>();
		for(FlightReservation reservation : reservationsList) {
			Double price = 0D;
			for(FlightSeat seat : reservation.getFlightReservationSeats()) {
				if(seat.getFlightClass().equals("Business")) {
					if(reservation.getBaggageChecked()) {
						price = seat.getFlight().getBussinessClassPrice() + seat.getFlight().getBaggageOver20Price();
					} else {
						price = seat.getFlight().getBussinessClassPrice();
					}
				} else {
					if(reservation.getBaggageChecked()) {
						price = seat.getFlight().getEconomicClassPrice() + seat.getFlight().getBaggageOver20Price();
					} else {
						price = seat.getFlight().getEconomicClassPrice();
					}
				}
			}
			retVal.add(new UserFlightReservationDTO(reservation.getId(),
					reservation.getFlightReservationSeats(),
					reservation.getRate(),
					reservation.getName(),
					reservation.getLastname(),
					reservation.getStatus().toString(), price));
			
		}
		
		return new ResponseEntity<List<UserFlightReservationDTO>>(retVal, HttpStatus.OK);
	}
	
	@PutMapping("/cancelFlight/{resId}")
	public ResponseEntity<?> cancelFlightReservation(@PathVariable("resId") Long resId) {
		if(!resService.cancelReservation(resId))
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("/rateFlight")
	public ResponseEntity<?> rateFlight(@RequestBody FlightRatingDTO ratingDto) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		User user = userDetails.getUser();
		if(!resService.rateFlight(ratingDto, user))
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("/rateAccomodation/{resId}/{rating}")
	public ResponseEntity<?> rateAcc(@PathVariable("resId") Long resId,
			@PathVariable("rating") Integer rating) {
		if(!resService.rateAcc(resId, rating))
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("/cancelAccomodation/{resId}")
	public ResponseEntity<?> cancelAcc(@PathVariable("resId") Long resId) {
		if(!resService.cancelAccReservation(resId))
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/retrieveUserAccReservations")
	public ResponseEntity<?> retrieveuserAccReservations() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		User user = userDetails.getUser();
		List<HotelReservation> reservations = resService.retrieveUserHotelReservations(user);
		List<UserHotelReservationDTO> retVal = new ArrayList<UserHotelReservationDTO>();
		for(HotelReservation reservation : reservations) {
			
			retVal.add(new UserHotelReservationDTO(reservation.getId(),
					reservation.getRoom(),
					reservation.getArrivalDate(),
					reservation.getDepartingDate(),
					reservation.getStatus().toString(),
					reservation.getRating(),
					reservation.getServices()
					));
		}
		return new ResponseEntity<List<UserHotelReservationDTO>>(retVal, HttpStatus.OK);
	}
	
	@GetMapping("/getFastReservations/{hotelId}")
	public ResponseEntity<?> getFastReservationsForHotel(@PathVariable("hotelId") Long id) {
		List<UserHotelReservationDTO> reservations = resService.retrieveFastReservationsForHotel(id);
		if(reservations == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<List<UserHotelReservationDTO>>(reservations, HttpStatus.OK);
	}
	
	@PutMapping("/fastHotelReserve/{resId}")
	public ResponseEntity<?> fastHotelReserve(@PathVariable("resId") Long id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		List<UserHotelReservationDTO> ret;
		User user = userDetails.getUser();
		try {
			ret = resService.fastHotelReserve(id, user);
		} catch(StaleObjectStateException exp) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		if(ret == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<List<UserHotelReservationDTO>>(ret, HttpStatus.OK);
	}
	
}

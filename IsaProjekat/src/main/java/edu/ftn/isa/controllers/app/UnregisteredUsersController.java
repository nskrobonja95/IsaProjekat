package edu.ftn.isa.controllers.app;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.ftn.isa.dto.AvailableRoomDTO;
import edu.ftn.isa.dto.AvioCompanyDTO;
import edu.ftn.isa.dto.DestinationDTO;
import edu.ftn.isa.dto.FlightReservationDTO;
import edu.ftn.isa.dto.HotelDTO;
import edu.ftn.isa.dto.InviteFriendFlightReservationDTO;
import edu.ftn.isa.dto.MultiCitySearchDTO;
import edu.ftn.isa.dto.RentACarServiceDTO;
import edu.ftn.isa.dto.RoundTripFlights;
import edu.ftn.isa.dto.RoundTripSearchDTO;
import edu.ftn.isa.dto.SearchAvailableRoomsForHotelDTO;
import edu.ftn.isa.dto.SearchHotelRequestDTO;
import edu.ftn.isa.dto.SearchHotelResponseDTO;
import edu.ftn.isa.dto.SeatDTO;
import edu.ftn.isa.dto.SeatRowDTO;
import edu.ftn.isa.model.AvioCompany;
import edu.ftn.isa.model.Destination;
import edu.ftn.isa.model.Flight;
import edu.ftn.isa.model.FlightReservation;
import edu.ftn.isa.model.FlightSeat;
import edu.ftn.isa.model.Hotel;
import edu.ftn.isa.model.HotelService;
import edu.ftn.isa.model.RentACarService;
import edu.ftn.isa.model.ReservationStatus;
import edu.ftn.isa.model.Room;
import edu.ftn.isa.model.User;
import edu.ftn.isa.repositories.AvioRepository;
import edu.ftn.isa.repositories.DestinationRepository;
import edu.ftn.isa.repositories.FlightRepository;
import edu.ftn.isa.repositories.FlightReservationRepository;
import edu.ftn.isa.repositories.FlightSeatRepository;
import edu.ftn.isa.repositories.HotelRepository;
import edu.ftn.isa.repositories.HotelServicesRepository;
import edu.ftn.isa.repositories.RentACarRepository;
import edu.ftn.isa.repositories.RoomRepository;
import edu.ftn.isa.repositories.UserRepository;
import edu.ftn.isa.security.CustomUserDetails;
import edu.ftn.isa.services.EmailService;

@RestController
@RequestMapping("/app")
public class UnregisteredUsersController {

	@Autowired
	private AvioRepository avioRepo;
	
	@Autowired
	private FlightRepository flightRepo;
	
	@Autowired
	private FlightReservationRepository flightResRepo;
	
	@Autowired
	private HotelRepository hotelRepo;
	
	@Autowired
	private RentACarRepository rentACarRepo;
	
	@Autowired
	private DestinationRepository destRepo;
	
	@Autowired
	private RoomRepository roomRepo;
	
	@Autowired
	private HotelServicesRepository hsRepo;
	
	@Autowired
	private FlightSeatRepository flightSeatRepo;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private UserRepository userRepo;
	
	@GetMapping("/airlines")
	public ResponseEntity<?> getAllAirlines() {
		List<AvioCompany> avios = avioRepo.findAll();
		List<AvioCompanyDTO> retVal = new ArrayList<AvioCompanyDTO>();
		AvioCompanyDTO temp;
		for(AvioCompany avio : avios) {
			//System.out.println(avio);
			temp = new AvioCompanyDTO();
			temp.setId(avio.getId());
			temp.setAddress(avio.getAddress());
			temp.setName(avio.getName());
			temp.setPromo(avio.getPromo());
			temp.setAdmin(avio.getAdmin());
			retVal.add(temp);
		}
		return new ResponseEntity<List<AvioCompanyDTO>>(retVal, HttpStatus.OK);
	}
	@GetMapping("airlines/{id}")
	public ResponseEntity<?> getAvio(
			@PathVariable("id") Long id) {
		Optional<AvioCompany> optionalAvio = avioRepo.findById(id);
		//System.out.println(optionalAvio);
		if(!optionalAvio.isPresent())
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<AvioCompany>(optionalAvio.get(), HttpStatus.OK);
	}
	@GetMapping("hotels/{id}")
	public ResponseEntity<?> getHotel(
			@PathVariable("id") Long id) {
		
		Optional<Hotel> optionalHotel = hotelRepo.findById(id);
		if(!optionalHotel.isPresent())
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		HotelDTO hotelDto = HotelDTO.parseHotel(optionalHotel.get());
		
		return new ResponseEntity<HotelDTO>(hotelDto, HttpStatus.OK);
		
	}
	@GetMapping("getAllDestinations")
	public ResponseEntity<?> getAllDestinations(){
		List<Destination> allDests = destRepo.findByDeleted(false);
		List<DestinationDTO> retVal = new ArrayList<DestinationDTO>();
		for(Destination d : allDests) {
			retVal.add(new DestinationDTO(d.getName()));
			
		}
		
		return new ResponseEntity<List<DestinationDTO>>(retVal, HttpStatus.OK);
	}
	
	@GetMapping("/getAllDestinationsById/{id}")
	public ResponseEntity<?> getDestinationsOfAvioCompany(
			@PathVariable("id") Long avioId) {
		Optional<AvioCompany> avio = avioRepo.findById(avioId);
		//System.out.println("Ovo su avio kompanije:"+avio);
		if(!avio.isPresent())
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		AvioCompany avioComp = avio.get();
		List<DestinationDTO> retVal = new ArrayList<DestinationDTO>();
		for(Destination d: avioComp.getDestinations()) {
			//System.out.println("ovo su destinacije:"+d);
			DestinationDTO dest = new DestinationDTO();
			dest.setName(d.getName());
			retVal.add(dest);
		}
		return new ResponseEntity<List<DestinationDTO>>(retVal, HttpStatus.OK);
	}
	
	@GetMapping("/getAllDestinations/{name}")
	public ResponseEntity<?> getDestinationsOfAvioCompanyByName(
			@PathVariable("name") String avioName) {
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
	
	@GetMapping("/hotels")
	public ResponseEntity<?> getAllHotels() {
		List<Hotel> hotels = hotelRepo.findAll();
		List<HotelDTO> retVal = new ArrayList<HotelDTO>();
		
		for(Hotel hotel : hotels) {
			retVal.add(HotelDTO.parseHotel(hotel));
		}
		return new ResponseEntity<List<HotelDTO>>(retVal, HttpStatus.OK);
	}
	
	@GetMapping("/carHire")
	public ResponseEntity<?> getAllRentACar() {
		List<RentACarService> rentACars = rentACarRepo.findAll();
		List<RentACarServiceDTO> retVal = new ArrayList<RentACarServiceDTO>();
		
		for(RentACarService rentACar : rentACars) {
			retVal.add(RentACarServiceDTO.parseRentACar(rentACar));
		}
		return new ResponseEntity<List<RentACarServiceDTO>>(retVal, HttpStatus.OK);
	}
	
	@PostMapping("/roundTripSearch")
	public ResponseEntity<?> searchFlights(@RequestBody RoundTripSearchDTO searchDto) throws ParseException {
		Date departDate = new SimpleDateFormat("yyyy-MM-dd").parse(searchDto.getDepartDate());
		Date returnDate = new SimpleDateFormat("yyyy-MM-dd").parse(searchDto.getReturnDate());
		Destination from = destRepo.findByNameAndDeleted(searchDto.getFrom(), false);
		Destination to = destRepo.findByNameAndDeleted(searchDto.getTo(), false);
		List<Flight> filteredFlights = flightRepo.roundTripSearch(departDate, from, to);
		List<Flight> filteredReturnFlights = flightRepo.roundTripSearch(returnDate, to, from);
		List<Flight> retValFlights = new ArrayList<Flight>();
		List<Flight> retValReturnFlights = new ArrayList<Flight>();
		for(Flight f : filteredFlights) {
			int numOfAvailableSeats = 0;
			if(searchDto.getFlightClass().name().equals("Economic"))
				numOfAvailableSeats = flightSeatRepo.countNumOfAvailableEconomicSeatsForFlight(f);
			else if(searchDto.getFlightClass().name().equals("Business"))
				numOfAvailableSeats = flightSeatRepo.countNumOfAvailableBusinessSeatsForFlight(f);
			else
				numOfAvailableSeats = flightSeatRepo.countNumOfAvailableSeatsForFlight(f);
			if(numOfAvailableSeats >= searchDto.getNumOfPpl())
				retValFlights.add(f);
		}
		for(Flight f : filteredReturnFlights) {
			int numOfAvailableSeats = 0;
			if(searchDto.getFlightClass().name().equals("Economic"))
				numOfAvailableSeats = flightSeatRepo.countNumOfAvailableEconomicSeatsForFlight(f);
			else if(searchDto.getFlightClass().name().equals("Business"))
				numOfAvailableSeats = flightSeatRepo.countNumOfAvailableBusinessSeatsForFlight(f);
			else
				numOfAvailableSeats = flightSeatRepo.countNumOfAvailableSeatsForFlight(f);
			if(numOfAvailableSeats >= searchDto.getNumOfPpl())
				retValReturnFlights.add(f);
		}
		RoundTripFlights retVal = new RoundTripFlights();
		retVal.setDirectFlights(retValFlights);
		retVal.setReturnFlights(retValReturnFlights);
		return new ResponseEntity<RoundTripFlights>(retVal, HttpStatus.OK);
	}
	
	@PostMapping("/oneWaySearch")
	public ResponseEntity<?> searchFlightsOneWay(@RequestBody RoundTripSearchDTO searchDto) throws ParseException {
		Date takeoff = new SimpleDateFormat("yyyy-MM-dd").parse(searchDto.getDepartDate());
		List<Flight> filteredFlights = flightRepo.oneWaySearch(takeoff, destRepo.findByNameAndDeleted(searchDto.getFrom(), false), destRepo.findByNameAndDeleted(searchDto.getTo(), false));
		List<Flight> retVal = new ArrayList<Flight>();
		for(Flight f : filteredFlights) {
			int numOfAvailableSeats = 0;
			if(searchDto.getFlightClass().name().equals("Economic"))
				numOfAvailableSeats = flightSeatRepo.countNumOfAvailableEconomicSeatsForFlight(f);
			else if(searchDto.getClass().equals("Business"))
				numOfAvailableSeats = flightSeatRepo.countNumOfAvailableBusinessSeatsForFlight(f);
			else
				numOfAvailableSeats = flightSeatRepo.countNumOfAvailableSeatsForFlight(f);
			if(numOfAvailableSeats >= searchDto.getNumOfPpl())
				retVal.add(f);
		}
		return new ResponseEntity<List<Flight>>(retVal, HttpStatus.OK);
	}
	
	@PostMapping("/multiCitySearch")
	public ResponseEntity<?> searchFlightsMultiCity(@RequestBody MultiCitySearchDTO searchDto) throws ParseException {
		Date takeoff1 = new SimpleDateFormat("yyyy-MM-dd").parse(searchDto.getDepartDate1());
		Date takeoff2 = new SimpleDateFormat("yyyy-MM-dd").parse(searchDto.getDepartDate2());
		Destination from = destRepo.findByNameAndDeleted(searchDto.getFrom(), false);
		Destination midDest = destRepo.findByNameAndDeleted(searchDto.getMidDest(), false);
		Destination to = destRepo.findByNameAndDeleted(searchDto.getTo(), false);
		List<Flight> filteredFlights = flightRepo.oneWaySearch(takeoff1, from, midDest);
		List<Flight> filteredFlights2 = flightRepo.oneWaySearch(takeoff2, midDest, to);
		List<Flight> retValFlights = new ArrayList<Flight>();
		List<Flight> retValFlights2 = new ArrayList<Flight>();
		for(Flight f : filteredFlights) {
			int numOfAvailableSeats = 0;
			if(searchDto.getFlightClass().name().equals("Economic"))
				numOfAvailableSeats = flightSeatRepo.countNumOfAvailableEconomicSeatsForFlight(f);
			else if(searchDto.getFlightClass().name().equals("Business"))
				numOfAvailableSeats = flightSeatRepo.countNumOfAvailableBusinessSeatsForFlight(f);
			else
				numOfAvailableSeats = flightSeatRepo.countNumOfAvailableSeatsForFlight(f);
			if(numOfAvailableSeats >= searchDto.getNumOfPpl())
				retValFlights.add(f);
		}
		for(Flight f : filteredFlights2) {
			int numOfAvailableSeats = 0;
			if(searchDto.getFlightClass().name().equals("Economic"))
				numOfAvailableSeats = flightSeatRepo.countNumOfAvailableEconomicSeatsForFlight(f);
			else if(searchDto.getFlightClass().name().equals("Business"))
				numOfAvailableSeats = flightSeatRepo.countNumOfAvailableBusinessSeatsForFlight(f);
			else
				numOfAvailableSeats = flightSeatRepo.countNumOfAvailableSeatsForFlight(f);
			if(numOfAvailableSeats >= searchDto.getNumOfPpl())
				retValFlights2.add(f);
		}
		
		RoundTripFlights retVal = new RoundTripFlights();
		retVal.setDirectFlights(filteredFlights);
		retVal.setReturnFlights(filteredFlights2);
		return new ResponseEntity<RoundTripFlights>(retVal, HttpStatus.OK);
	}
	
	@PostMapping("/searchAvailableRooms")
	public ResponseEntity<?> searchRooms(@RequestBody SearchHotelRequestDTO searchDto) throws ParseException {
		Date checkInDate = new SimpleDateFormat("yyyy-MM-dd").parse(searchDto.getCheckIn());
		Date checkOutDate = new SimpleDateFormat("yyyy-MM-dd").parse(searchDto.getCheckOut());
		List<Room> availableRooms = roomRepo.searchAvailableRooms(checkInDate, checkOutDate, destRepo.findByNameAndDeleted(searchDto.getDest(), false));
		return new ResponseEntity<List<Room>>(availableRooms, HttpStatus.OK);
	}
	
	@PostMapping("/searchHotels")
	public ResponseEntity<?> searchHotels(@RequestBody SearchHotelRequestDTO searchDto) throws ParseException {
		Date checkInDate = new SimpleDateFormat("yyyy-MM-dd").parse(searchDto.getCheckIn());
		Date checkOutDate;
		if(searchDto.getCheckOut() != null)
			checkOutDate = new SimpleDateFormat("yyyy-MM-dd").parse(searchDto.getCheckOut());
		else {
			Calendar c = Calendar.getInstance();
			c.setTime(checkInDate);
			c.add(Calendar.DATE, 5);
			checkOutDate = c.getTime();
		}
		List<Hotel> availableHotels = hotelRepo.searchAvailableHotels(checkInDate, checkOutDate, destRepo.findByNameAndDeleted(searchDto.getDest(), false));
		SearchHotelResponseDTO response = new SearchHotelResponseDTO();
		response.setCheckIn(searchDto.getCheckIn());
		response.setCheckOut(searchDto.getCheckOut());
		List<HotelDTO> availableHotelsDto = new ArrayList<HotelDTO>();
		for(int i=0; i<availableHotels.size(); ++i) {
			HotelDTO hotelDto = new HotelDTO();
			hotelDto.setName(availableHotels.get(i).getName());
			hotelDto.setAddress(availableHotels.get(i).getAddress());
			hotelDto.setId(availableHotels.get(i).getId());
			hotelDto.setPromo(availableHotels.get(i).getPromo());
			availableHotelsDto.add(hotelDto);
		}
		response.setHotels(availableHotelsDto);
		return new ResponseEntity<SearchHotelResponseDTO>(response, HttpStatus.OK);
	}
	
	@PostMapping("/searchAvailableRoomsForHotel")
	public ResponseEntity<?> searchRooms(@RequestBody SearchAvailableRoomsForHotelDTO searchDto) throws ParseException {
		Date checkInDate = null;
		Date checkOutDate = null;
		try {
			checkInDate = new SimpleDateFormat("yyyy-MM-dd").parse(searchDto.getCheckIn());
			checkOutDate = new SimpleDateFormat("yyyy-MM-dd").parse(searchDto.getCheckOut());
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		List<Room> availableRooms = roomRepo.searchAvailableRoomsForHotel(checkInDate, checkOutDate, searchDto.getHotel());
		List<AvailableRoomDTO> availableRoomsDto = new ArrayList<AvailableRoomDTO>();
		for(int i=0; i<availableRooms.size(); ++i) {
			AvailableRoomDTO room = new AvailableRoomDTO();
			room.setId(availableRooms.get(i).getId());
			room.setBalcony(availableRooms.get(i).isBalcony());
			room.setHotelServices(availableRooms.get(i).getHotelServices());
			room.setDescription(availableRooms.get(i).getDescription());
			room.setNumOfBeds(availableRooms.get(i).getNumOfBeds());
			room.setPrices(availableRooms.get(i).getPrices());
			availableRoomsDto.add(room);
		}
		return new ResponseEntity<List<AvailableRoomDTO>>(availableRoomsDto, HttpStatus.OK);
	}
	
	@GetMapping("/loadHotelServices/{hotelId}")
	public ResponseEntity<?> loadHotelServices(@PathVariable("hotelId") Long hotelId) {
		Hotel h = hotelRepo.findById(hotelId).get();
		List<HotelService> services = hsRepo.findByHotel(h);
		return new ResponseEntity<List<HotelService>>(services, HttpStatus.OK);
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
		Optional<Flight> optionalFlight = flightRepo.findById(flightId);
		if(!optionalFlight.isPresent()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Flight f = optionalFlight.get();
		List<FlightSeat> seats = flightSeatRepo.findByFlight(optionalFlight.get());
		int numOfCols = seats.size()/f.getNumOfRows();
		List<SeatRowDTO> seatRows = new ArrayList<SeatRowDTO>();
		SeatRowDTO seatRow = new SeatRowDTO();
		for(int j=0; j<seats.size(); ++j) {
			SeatDTO seatDto = new SeatDTO();
			seatDto.setId(seats.get(j).getId());
			seatDto.setColNum(seats.get(j).getColNo());
			seatDto.setFastRes(seats.get(j).isFastReservation());
			seatDto.setFlightClass(seats.get(j).getFlightClass().toString());
			seatDto.setRowNum(seats.get(j).getRowNo());
			seatDto.setSeatNumber(seats.get(j).getSeatNumber());
			seatDto.setAvailable(seats.get(j).isAvailable());
			seatDto.setReserved(false);
			seatDto.setFlight(f);
			seatRow.getSeats().add(seatDto);
			if(seats.get(j).getSeatNumber()%numOfCols == 0) {
				seatRows.add(seatRow);
				seatRow = new SeatRowDTO();
			}
		}
		return new ResponseEntity<List<SeatRowDTO>>(seatRows, HttpStatus.OK);
	}
	
	@PostMapping("/reserve")
	public ResponseEntity<?> reserve(@RequestBody FlightReservationDTO flightDto) throws ParseException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		User user = userDetails.getUser();
		FlightReservation reservation = new FlightReservation();
		reservation.setUser(user);
		reservation.setName(flightDto.getName());
		reservation.setLastname(flightDto.getLastname());
		reservation.setPassportNumber(flightDto.getPassportNumber());
		reservation.setStatus(ReservationStatus.APPROVED);
		List<FlightSeat> seats = new ArrayList<FlightSeat>();
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date todayDate = new Date();
		Date reserveDate = formatter.parse(formatter.format(todayDate));
		reservation.setReserveDate(reserveDate);
		for(int i=0; i<flightDto.getSeats().size(); ++i) {
			Optional<FlightSeat> optionalSeat = flightSeatRepo.findById(flightDto.getSeats().get(i).getId());
			if(!optionalSeat.isPresent())
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			FlightSeat seat = optionalSeat.get();
			seat.setAvailable(false);
			seats.add(optionalSeat.get());
		}
		reservation.setFlightReservationSeats(seats);
		flightResRepo.save(reservation);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("/sendInvitation")
	public ResponseEntity<?> sendInvitation(@RequestBody InviteFriendFlightReservationDTO reservationDto) {
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
		}
		if(reservationDto.getSeats().size() == 1) {
			confirm += "0/";
			refuse += "0/";
		}
		confirm += reservationDto.getName() + "/" + reservationDto.getLastname() + "/" +
					reservationDto.getPassportNumber() + "/" + reservationDto.getUsername();
		refuse += "terminal";
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
	
	@GetMapping(value="/acceptInvitation/{dirFlightSeatId}/{retFlightSeatId}",  produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String acceptInvitation(@PathVariable("dirFlightSeatId") Long dirSeatId,
			@PathVariable("retFlightSeatId") Long retSeatId) throws ParseException {
		List<FlightSeat> flightSeats = new ArrayList<FlightSeat>();
		Optional<FlightSeat> optionalDirSeat = flightSeatRepo.findById(dirSeatId);
		flightSeats.add(optionalDirSeat.get());
		Optional<FlightSeat> optionalRetSeat = flightSeatRepo.findById(retSeatId);
		if(optionalRetSeat.isPresent())
			flightSeats.add(optionalRetSeat.get());
		FlightReservation reservation = flightResRepo.findByFlightReservationSeatsIn(flightSeats);
		if(reservation.getStatus() == ReservationStatus.APPROVED ) {
			return "<div>You have already confirmed this invitation.</div>";
		}
		
		if(reservation.getStatus() == ReservationStatus.CANCELED) {
			return "<div>You have already declined this invitation.</div>";
		}
		reservation.setStatus(ReservationStatus.APPROVED);
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date todayDate = new Date();
		Date reserveDate = formatter.parse(formatter.format(todayDate));
		reservation.setReserveDate(reserveDate);
		flightResRepo.save(reservation);
		return "<div>RESERVATION SUCCESSFUL</div>";
	}
	
	@GetMapping(value="/refuseInvitation/{dirFlightSeatId}/{retFlightSeatId}",  produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String refuseInvitation(@PathVariable("dirFlightSeatId") Long dirSeatId,
			@PathVariable("retFlightSeatId") Long retSeatId) {
		List<FlightSeat> flightSeats = new ArrayList<FlightSeat>();
		Optional<FlightSeat> optionalDirSeat = flightSeatRepo.findById(dirSeatId);
		FlightSeat dirSeat = optionalDirSeat.get();
		flightSeats.add(dirSeat);
		Optional<FlightSeat> optionalRetSeat = flightSeatRepo.findById(retSeatId);
		if(optionalRetSeat.isPresent()) {
			flightSeats.add(optionalRetSeat.get());
		}
		FlightReservation reservation = flightResRepo.findByFlightReservationSeatsIn(flightSeats);
		if(reservation.getStatus() == ReservationStatus.APPROVED ) {
			return "<div>You have already confirmed this invitation.</div>";
		}
		
		if(reservation.getStatus() == ReservationStatus.CANCELED) {
			return "<div>You have already declined this invitation."
					+ " You can cancel it through Flight App.</div>";
		}
		reservation.setStatus(ReservationStatus.CANCELED);
		for(int i=0; i<reservation.getFlightReservationSeats().size(); ++i) {
			reservation.getFlightReservationSeats().get(i).setAvailable(true);
		}
		flightResRepo.save(reservation);
		return "<div>RESERVATION CANCELED</div>";
	}
	
}
package edu.ftn.isa.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import edu.ftn.isa.constants.FlightResConstants;
import edu.ftn.isa.constants.HotelConstants;
import edu.ftn.isa.constants.UserConstants;
import edu.ftn.isa.dto.FlightReservationDTO;
import edu.ftn.isa.dto.FlightReservationResponseDTO;
import edu.ftn.isa.dto.HotelReservationDTO;
import edu.ftn.isa.dto.SeatDTO;
import edu.ftn.isa.dto.UserHotelReservationDTO;
import edu.ftn.isa.model.FlightClass;
import edu.ftn.isa.model.FlightReservation;
import edu.ftn.isa.model.FlightSeat;
import edu.ftn.isa.model.Hotel;
import edu.ftn.isa.model.HotelReservation;
import edu.ftn.isa.model.HotelServiceModel;
import edu.ftn.isa.model.ReservationStatus;
import edu.ftn.isa.model.Role;
import edu.ftn.isa.model.Room;
import edu.ftn.isa.model.User;
import edu.ftn.isa.repositories.AvioRepository;
import edu.ftn.isa.repositories.FlightRateRepository;
import edu.ftn.isa.repositories.FlightRepository;
import edu.ftn.isa.repositories.FlightReservationRepository;
import edu.ftn.isa.repositories.FlightSeatRepository;
import edu.ftn.isa.repositories.HotelRepository;
import edu.ftn.isa.repositories.HotelReservationRepository;
import edu.ftn.isa.repositories.RoomRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestReservationService {

	@Mock
	private EntityManager em;

	@Mock
	FlightReservationRepository flightResRepo;
	
	@Mock
	FlightSeatRepository flightSeatRepo;
	
	@Mock
	FlightRepository flightRepo;
	
	@Mock
	FlightRateRepository flightRateRepo;
	
	@Mock
	AvioRepository avioRepo;
	
	@Mock
	HotelReservationRepository hotelResRepo;
	
	@Mock
	private RoomRepository roomRepo;
	
	@Mock
	private HotelRepository hotelRepo;
	
	@InjectMocks
	private ReservationService resService;
	
	@Test
	public void testCancelFlightRes() throws ParseException {
		
		
		EntityManager mocked = mock(EntityManager.class);
		
		User user = new User();
		user.setUsername(UserConstants.NEW_USERNAME);
		user.setCity(UserConstants.NEW_CITY);
		user.setPhoneNumber(UserConstants.NEW_PHONE);
		user.setName(UserConstants.NEW_FIRST_NAME);
		user.setLastname(UserConstants.NEW_LAST_NAME);
		user.setEmail(UserConstants.NEW_EMAIL);
//		user.setPassword(passwordEncoder.encode(UserConstants.NEW_PASSWORD));
		user.setEnabled(true);
		user.setPasswordChanged(false);
		user.setRole(Role.User);
		
		FlightReservation reservation = new FlightReservation();
		reservation.setUser(user);
		reservation.setName(FlightResConstants.NAME);
		reservation.setLastname(FlightResConstants.LASTNAME);
		reservation.setPassportNumber(FlightResConstants.PASSPORTNUM);
		reservation.setRate(0);
		reservation.setStatus(ReservationStatus.APPROVED);
		reservation.setId(1L);
//		List<FlightSeat> seats = new ArrayList<FlightSeat>();
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date todayDate = new Date();
		Date reserveDate = formatter.parse(formatter.format(todayDate));
		reservation.setReserveDate(reserveDate);
		
		FlightSeat fs = new FlightSeat();
		fs.setAvailable(true);
		fs.setFastReservation(false);
		fs.setFlight(null);
		fs.setColNo(1);
		fs.setRowNo(1);
		fs.setSeatNumber(1);
		fs.setFlightClass(FlightClass.Business);
		fs.setId(1L);
		
		reservation.setFlightReservationSeats(Arrays.asList(fs));
		
		FlightReservationDTO dto = new FlightReservationDTO();
		dto.setName(FlightResConstants.NAME);
		dto.setLastname(FlightResConstants.LASTNAME);
		dto.setPassportNumber(FlightResConstants.PASSPORTNUM);
		SeatDTO seatDto = new SeatDTO();
		seatDto.setAvailable(true);
		seatDto.setColNum(1);
		seatDto.setFastRes(false);
		seatDto.setSeatNumber(1);
		seatDto.setFlightClass("Business");
		seatDto.setId(1L);
		dto.setSeats(Arrays.asList(seatDto));
		
//		when(em.find(FlightSeat.class, new Long(1), LockModeType.OPTIMISTIC)).thenReturn(fs);
//		when(flightResRepo.save(reservation)).thenReturn(reservation);
//		
//		FlightReservationResponseDTO flag1 = resService.reserveFlightSeat(Arrays.asList(dto), user);
//		assertEquals(true, flag1.isSuccesfullyReserved());
		
		reservation.setFlightReservationSeats(Arrays.asList(fs));
		
		List<FlightReservation> reservations = new ArrayList<FlightReservation>();
		
		when(flightResRepo.findById(1L)).thenReturn(Optional.of(reservation));
		when(flightResRepo.save(reservation)).thenReturn(reservation);
		when(flightResRepo.findByHotelReservationAndStatus(null, ReservationStatus.APPROVED)).thenReturn(reservations);
		
		boolean flag = resService.cancelReservation(1L);
		
		assertEquals(true, flag);
	}
	
//	@Test
//	public void testReserveFlightSeat() throws ParseException {
//		User user = new User();
//		user.setUsername(UserConstants.NEW_USERNAME);
//		user.setCity(UserConstants.NEW_CITY);
//		user.setPhoneNumber(UserConstants.NEW_PHONE);
//		user.setName(UserConstants.NEW_FIRST_NAME);
//		user.setLastname(UserConstants.NEW_LAST_NAME);
//		user.setEmail(UserConstants.NEW_EMAIL);
////		user.setPassword(passwordEncoder.encode(UserConstants.NEW_PASSWORD));
//		user.setEnabled(true);
//		user.setPasswordChanged(false);
//		user.setRole(Role.User);
//		
//		FlightReservation reservation = new FlightReservation();
//		reservation.setUser(user);
//		reservation.setName(FlightResConstants.NAME);
//		reservation.setLastname(FlightResConstants.LASTNAME);
//		reservation.setPassportNumber(FlightResConstants.PASSPORTNUM);
//		reservation.setRate(0);
//		reservation.setStatus(ReservationStatus.APPROVED);
//		reservation.setId(1L);
//		FlightReservation reservationToSave = new FlightReservation();
//		reservationToSave.setUser(user);
//		reservationToSave.setName(FlightResConstants.NAME);
//		reservationToSave.setLastname(FlightResConstants.LASTNAME);
//		reservationToSave.setPassportNumber(FlightResConstants.PASSPORTNUM);
//		reservationToSave.setRate(0);
//		reservationToSave.setStatus(ReservationStatus.APPROVED);
//		reservationToSave.setId(null);
////		List<FlightSeat> seats = new ArrayList<FlightSeat>();
//		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm");
//		Date todayDate = new Date();
//		Date reserveDate = formatter.parse(formatter.format(todayDate));
//		reservation.setReserveDate(reserveDate);
//		
//		FlightSeat fs = new FlightSeat();
//		fs.setAvailable(true);
//		fs.setFastReservation(false);
//		fs.setFlight(null);
//		fs.setColNo(1);
//		fs.setRowNo(1);
//		fs.setSeatNumber(1);
//		fs.setFlightClass(FlightClass.Business);
//		fs.setId(1L);
//		fs.setVersion(0);
//		
//		reservation.setFlightReservationSeats(Arrays.asList(fs));
//		reservationToSave.setFlightReservationSeats(Arrays.asList(fs));
//		
//		FlightReservationDTO dto = new FlightReservationDTO();
//		dto.setName(FlightResConstants.NAME);
//		dto.setLastname(FlightResConstants.LASTNAME);
//		dto.setPassportNumber(FlightResConstants.PASSPORTNUM);
//		SeatDTO seatDto = new SeatDTO();
//		seatDto.setAvailable(true);
//		seatDto.setColNum(1);
//		seatDto.setFastRes(false);
//		seatDto.setSeatNumber(1);
//		seatDto.setFlightClass("Business");
//		seatDto.setId(1L);
//		dto.setSeats(Arrays.asList(seatDto));
//		
//		when(em.find(FlightSeat.class, new Long(1), LockModeType.OPTIMISTIC)).thenReturn(fs);
//		when(flightResRepo.save(any())).thenReturn(reservation);
//		
//		FlightReservationResponseDTO flag1 = resService.reserveFlightSeat(Arrays.asList(dto), user);
//		assertEquals(true, flag1.isSuccesfullyReserved());
//		
//		verify(flightResRepo, times(1)).save(reservation);
//	}
	
	@Test
	public void testReserveRoom() throws ParseException {
		User user = new User();
		user.setUsername(UserConstants.NEW_USERNAME);
		user.setCity(UserConstants.NEW_CITY);
		user.setPhoneNumber(UserConstants.NEW_PHONE);
		user.setName(UserConstants.NEW_FIRST_NAME);
		user.setLastname(UserConstants.NEW_LAST_NAME);
		user.setEmail(UserConstants.NEW_EMAIL);
//		user.setPassword(passwordEncoder.encode(UserConstants.NEW_PASSWORD));
		user.setEnabled(true);
		user.setPasswordChanged(false);
		user.setRole(Role.User);
		
		Hotel hotel = new Hotel();
		hotel.setAdmin(user);
		hotel.setName(HotelConstants.NAME);
		hotel.setPromo(HotelConstants.PROMO);
		hotel.setAddress(HotelConstants.ADDRESS);
		
		Room room = new Room();
		room.setId(1L);
		room.setBalcony(true);
		room.setDescription("Description");
		room.setHotel(hotel);
		
		HotelReservationDTO reservationDto = new HotelReservationDTO();
		Date arrive = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2019-27-09 10:00");
		Date depart = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2019-02-10 10:00");
		reservationDto.setArrivalDate(arrive);
		reservationDto.setDepartingDate(depart);
		reservationDto.setFlightReservationIds(Arrays.asList(1));
		reservationDto.setRoomId(1L);
		reservationDto.setHotelServices(Arrays.asList());
		
		FlightReservation freservation = new FlightReservation();
		freservation.setUser(user);
		freservation.setName(FlightResConstants.NAME);
		freservation.setLastname(FlightResConstants.LASTNAME);
		freservation.setPassportNumber(FlightResConstants.PASSPORTNUM);
		freservation.setRate(0);
		freservation.setStatus(ReservationStatus.APPROVED);
		freservation.setId(1L);
//		List<FlightSeat> seats = new ArrayList<FlightSeat>();
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date todayDate = new Date();
		Date reserveDate = formatter.parse(formatter.format(todayDate));
		freservation.setReserveDate(reserveDate);
				
		HotelReservation reservation = new HotelReservation();
		reservation.setCanceled(false);
		reservation.setUser(user);
		reservation.setRoom(room);
		reservation.setStatus(ReservationStatus.APPROVED);
		reservation.setArrivalDate(reservationDto.getArrivalDate());
		reservation.setDepartingDate(reservationDto.getDepartingDate());
		List<HotelServiceModel> services = new ArrayList<HotelServiceModel>();
//		for(HotelServiceModel service : room.getHotelServices()) {
//			if(reservationDto.getHotelServices().contains(service.getName()))
//				services.add(service);
//		}
		reservation.setServices(services);
		
		when(em.find(Room.class, 1L, LockModeType.PESSIMISTIC_WRITE)).thenReturn(room);
		when(hotelResRepo.save(reservation)).thenReturn(reservation);
		when(flightResRepo.findById(1L)).thenReturn(Optional.of(freservation));
		when(flightResRepo.save(freservation)).thenReturn(freservation);
	}
	
	@Test
	public void testRetrieveRes() throws ParseException {
		User user = new User();
		user.setUsername(UserConstants.NEW_USERNAME);
		user.setCity(UserConstants.NEW_CITY);
		user.setPhoneNumber(UserConstants.NEW_PHONE);
		user.setName(UserConstants.NEW_FIRST_NAME);
		user.setLastname(UserConstants.NEW_LAST_NAME);
		user.setEmail(UserConstants.NEW_EMAIL);
//		user.setPassword(passwordEncoder.encode(UserConstants.NEW_PASSWORD));
		user.setEnabled(true);
		user.setPasswordChanged(false);
		user.setRole(Role.User);
		
		Hotel hotel = new Hotel();
		hotel.setAdmin(user);
		hotel.setName(HotelConstants.NAME);
		hotel.setPromo(HotelConstants.PROMO);
		hotel.setAddress(HotelConstants.ADDRESS);
		
		Room room = new Room();
		room.setId(1L);
		room.setBalcony(true);
		room.setDescription("Description");
		room.setHotel(hotel);
		
		HotelReservation reservation = new HotelReservation();
		reservation.setCanceled(false);
		reservation.setUser(user);
		reservation.setRoom(room);
		reservation.setStatus(ReservationStatus.APPROVED);
		Date arrive = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2019-27-09 10:00");
		Date depart = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2019-02-10 10:00");
		reservation.setArrivalDate(arrive);
		reservation.setDepartingDate(depart);
		List<HotelServiceModel> services = new ArrayList<HotelServiceModel>();
//		for(HotelServiceModel service : room.getHotelServices()) {
//			if(reservationDto.getHotelServices().contains(service.getName()))
//				services.add(service);
//		}
		reservation.setServices(services);
		
		UserHotelReservationDTO dto = new UserHotelReservationDTO(reservation.getId(),
				reservation.getRoom(),
				reservation.getArrivalDate(),
				reservation.getDepartingDate(),
				reservation.getStatus().toString(),
				reservation.getRating(),
				reservation.getServices()
				);
		when(hotelRepo.findById(1L)).thenReturn(Optional.of(hotel));
		when(hotelResRepo.findByHotelAndStatus(hotel, ReservationStatus.PENDING)).thenReturn(Arrays.asList(reservation));
		List<UserHotelReservationDTO> retList = resService.retrieveFastReservationsForHotel(1L);
		assertEquals(1, retList.size());
		
	}
	
	
}

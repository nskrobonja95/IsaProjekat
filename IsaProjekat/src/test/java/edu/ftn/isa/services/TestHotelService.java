package edu.ftn.isa.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import edu.ftn.isa.constants.HotelConstants;
import edu.ftn.isa.constants.UserConstants;
import edu.ftn.isa.dto.AddHotelDTO;
import edu.ftn.isa.dto.BasicHotelInfoDTO;
import edu.ftn.isa.model.Destination;
import edu.ftn.isa.model.Hotel;
import edu.ftn.isa.model.Role;
import edu.ftn.isa.model.User;
import edu.ftn.isa.repositories.DestinationRepository;
import edu.ftn.isa.repositories.HotelRepository;
import edu.ftn.isa.repositories.HotelReservationRepository;
import edu.ftn.isa.repositories.RoomRepository;
import edu.ftn.isa.repositories.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestHotelService {

	@Mock
	private HotelRepository hotelRepo;
	
	@Mock
	private UserRepository userRepo;
	
	@Mock
	private PasswordEncoder passwordEncoder;
	
	@Mock
	private DestinationRepository destRepo;
	
	@Mock
	private RoomRepository roomRepo;
	
	@Mock
	private HotelReservationRepository hotelResRepo;
	
	@InjectMocks
	private HotelService hotelService;
	
	@Test
	public void testAddHotel() {
		AddHotelDTO dto = new AddHotelDTO();
		dto.setName(HotelConstants.NAME);
		dto.setAddress(HotelConstants.ADDRESS);
		dto.setDestination(HotelConstants.DESTINATION);
		dto.setEmail(UserConstants.NEW_EMAIL);
		dto.setPassword(UserConstants.NEW_PASSWORD);
		dto.setPromo(HotelConstants.PROMO);
		dto.setUsername(UserConstants.NEW_USERNAME);
		
		User user  = new User();
		user.setEmail(UserConstants.NEW_EMAIL);
		user.setUsername(UserConstants.NEW_USERNAME);
		user.setEnabled(true);
		user.setRole(Role.HotelAdmin);
		user.setPassword(passwordEncoder.encode(UserConstants.NEW_PASSWORD));
		user.setPasswordChanged(false);
		
		when(userRepo.save(user)).thenReturn(user);
		
		Hotel hotel = new Hotel();
		hotel.setAdmin(user);
		hotel.setName(dto.getName());
		hotel.setPromo(dto.getPromo());
		hotel.setAddress(dto.getAddress());
		
		Destination dest = new Destination();
		dest.setName(HotelConstants.DESTINATION);
		dest.setDeleted(false);
		dest.setId(1l);
		when(destRepo.findByNameAndDeleted(dto.getDestination(), false)).thenReturn(dest);
		hotel.setDestination(dest);
		
		when(hotelRepo.save(hotel)).thenReturn(hotel);
		
		Hotel h = hotelService.addHotel(dto);
		
		assertEquals(hotel, h);
		
		verify(userRepo, times(1)).save(user);
		verify(hotelRepo, times(1)).save(hotel);
		verify(destRepo, times(1)).findByNameAndDeleted(dto.getDestination(), false);
		verifyNoMoreInteractions(userRepo);
		verifyNoMoreInteractions(hotelRepo);
		verifyNoMoreInteractions(destRepo);
		
	}
	
	@Test
	public void testAddHotelWithExistingAdmin() {
		AddHotelDTO dto = new AddHotelDTO();
		dto.setName(HotelConstants.NAME);
		dto.setAddress(HotelConstants.ADDRESS);
		dto.setDestination(HotelConstants.DESTINATION);
		dto.setEmail(UserConstants.NEW_EMAIL);
		dto.setPassword(UserConstants.NEW_PASSWORD);
		dto.setPromo(HotelConstants.PROMO);
		dto.setUsername(UserConstants.NEW_USERNAME);
		dto.setExistingAdminId(1L);
		
		User user  = new User();
		user.setEmail(UserConstants.NEW_EMAIL);
		user.setUsername(UserConstants.NEW_USERNAME);
		user.setEnabled(true);
		user.setRole(Role.HotelAdmin);
		user.setPassword(passwordEncoder.encode(UserConstants.NEW_PASSWORD));
		user.setPasswordChanged(false);
		user.setId(1L);
		
//		when(userRepo.save(user)).thenReturn(user);
		
		Hotel hotel = new Hotel();
		hotel.setAdmin(user);
		hotel.setName(dto.getName());
		hotel.setPromo(dto.getPromo());
		hotel.setAddress(dto.getAddress());
		
		Destination dest = new Destination();
		dest.setName(HotelConstants.DESTINATION);
		dest.setDeleted(false);
		dest.setId(1l);
		when(destRepo.findByNameAndDeleted(dto.getDestination(), false)).thenReturn(dest);
		hotel.setDestination(dest);
		
		when(userRepo.findById(dto.getExistingAdminId())).thenReturn(Optional.of(user));
		when(hotelRepo.save(hotel)).thenReturn(hotel);
		
		Hotel h = hotelService.addHotel(dto);
		
		assertEquals(hotel, h);
		
//		verify(userRepo, times(1)).findById(1L);
		verify(hotelRepo, times(1)).save(hotel);
		verify(destRepo, times(1)).findByNameAndDeleted(dto.getDestination(), false);
//		verifyNoMoreInteractions(userRepo);
		verifyNoMoreInteractions(hotelRepo);
		verifyNoMoreInteractions(destRepo);
		
	}
	
	@Test
	public void testFindByAdmin() {
		User user  = new User();
		user.setEmail(UserConstants.NEW_EMAIL);
		user.setUsername(UserConstants.NEW_USERNAME);
		user.setEnabled(true);
		user.setRole(Role.HotelAdmin);
		user.setPassword(passwordEncoder.encode(UserConstants.NEW_PASSWORD));
		user.setPasswordChanged(false);
		user.setId(1L);
		
		Hotel hotel = new Hotel();
		hotel.setAdmin(user);
		hotel.setName(HotelConstants.NAME);
		hotel.setPromo(HotelConstants.PROMO);
		hotel.setAddress(HotelConstants.ADDRESS);
		
		when(hotelRepo.findHotelsByAdmin(user)).thenReturn(Arrays.asList(hotel));
		
		List<Hotel> hotels = hotelService.findHotelsByAdmin(user);
		
		assertEquals(1, hotels.size());
		
		verify(hotelRepo, times(1)).findHotelsByAdmin(user);
		verifyNoMoreInteractions(hotelRepo);
		
	}
	
	@Test
	public void testFindById() {
		User user  = new User();
		user.setEmail(UserConstants.NEW_EMAIL);
		user.setUsername(UserConstants.NEW_USERNAME);
		user.setEnabled(true);
		user.setRole(Role.HotelAdmin);
		user.setPassword(passwordEncoder.encode(UserConstants.NEW_PASSWORD));
		user.setPasswordChanged(false);
		user.setId(1L);
		
		Hotel hotel = new Hotel();
		hotel.setAdmin(user);
		hotel.setName(HotelConstants.NAME);
		hotel.setPromo(HotelConstants.PROMO);
		hotel.setAddress(HotelConstants.ADDRESS);
		hotel.setId(1L);
		
		when(hotelRepo.findById(1L)).thenReturn(Optional.of(hotel));
		
		Hotel h = hotelService.findHotelById(1L);
		
		assertEquals(h, hotel);
		
		verify(hotelRepo, times(1)).findById(1L);
		verifyNoMoreInteractions(hotelRepo);
		
	}
	
}

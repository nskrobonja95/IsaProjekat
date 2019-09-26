package edu.ftn.isa.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
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

import edu.ftn.isa.constants.AvioConstants;
import edu.ftn.isa.constants.DestinationConstants;
import edu.ftn.isa.constants.UserConstants;
import edu.ftn.isa.dto.BasicAvioInfoDTO;
import edu.ftn.isa.model.AvioCompany;
import edu.ftn.isa.model.Destination;
import edu.ftn.isa.model.FlightReservation;
import edu.ftn.isa.model.Role;
import edu.ftn.isa.model.User;
import edu.ftn.isa.repositories.AvioRepository;
import edu.ftn.isa.repositories.DestinationRepository;
import edu.ftn.isa.repositories.FlightReservationRepository;
import edu.ftn.isa.repositories.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestAvioService {

	@Mock
	private AvioCompany avio;
	
	@Mock
	private User user;
	
	@Mock
	private Destination dest;
	
	@Mock
	private AvioRepository avioRepo;
	
	@Mock
	private DestinationRepository destRepo;
	
	@Mock
	private FlightReservationRepository flightResRepo;
	
	@Mock
	private UserRepository userRepo;
	
	@Mock
	private PasswordEncoder passwordEncoder;
	
	@InjectMocks
	private AvioService avioService;
	
	@Test
	public void testGetAvioByAdmin() {
		user.setUsername(UserConstants.NEW_USERNAME);
		user.setCity(UserConstants.NEW_CITY);
		user.setPhoneNumber(UserConstants.NEW_PHONE);
		user.setName(UserConstants.NEW_FIRST_NAME);
		user.setLastname(UserConstants.NEW_LAST_NAME);
		user.setEmail(UserConstants.NEW_EMAIL);
		user.setPassword(passwordEncoder.encode(UserConstants.NEW_PASSWORD));
		user.setEnabled(true);
		user.setPasswordChanged(false);
		user.setRole(Role.User);
		avio.setAddress(AvioConstants.ADDRESS);
		avio.setAdmin(user);
		avio.setAverageRate(AvioConstants.AVGRATE);
		avio.setId(AvioConstants.ID);
		avio.setName(AvioConstants.NAME);
		avio.setPromo(AvioConstants.PROMO);
		when(avioRepo.findByAdmin(user)).thenReturn(avio);
		
		AvioCompany res = avioService.getAvioByAdmin(user);
		
		assertEquals(avio, res);
		
		verify(avioRepo, times(1)).findByAdmin(user);
        verifyNoMoreInteractions(avioRepo);
	}
	
	@Test
	public void testUpdateAvio() {
		user.setUsername(UserConstants.NEW_USERNAME);
		user.setCity(UserConstants.NEW_CITY);
		user.setPhoneNumber(UserConstants.NEW_PHONE);
		user.setName(UserConstants.NEW_FIRST_NAME);
		user.setLastname(UserConstants.NEW_LAST_NAME);
		user.setEmail(UserConstants.NEW_EMAIL);
		user.setPassword(passwordEncoder.encode(UserConstants.NEW_PASSWORD));
		user.setEnabled(true);
		user.setPasswordChanged(false);
		user.setRole(Role.User);
		avio.setAddress(AvioConstants.ADDRESS);
		avio.setAdmin(user);
		avio.setAverageRate(AvioConstants.AVGRATE);
		avio.setId(AvioConstants.ID);
		avio.setName(AvioConstants.NAME);
		avio.setPromo(AvioConstants.PROMO);
		BasicAvioInfoDTO dto = new BasicAvioInfoDTO();
		dto.setAddress(AvioConstants.UPDATE_ADDRESS);
		dto.setName(AvioConstants.UPDATE_NAME);
		dto.setPromo(AvioConstants.UPDATE_PROMO);
		AvioCompany modified = new AvioCompany();
		modified.setAddress(AvioConstants.UPDATE_ADDRESS);
		modified.setAdmin(user);
		modified.setAverageRate(AvioConstants.AVGRATE);
		modified.setId(AvioConstants.ID);
		modified.setName(AvioConstants.UPDATE_NAME);
		modified.setPromo(AvioConstants.UPDATE_PROMO);
		when(avioRepo.findByAdmin(user)).thenReturn(avio);
		when(avioRepo.save(avio)).thenReturn(modified);
		
		AvioCompany res = avioService.updateAvio(dto, user);
		
		assertEquals(modified, res);
		
		verify(avioRepo, times(1)).save(avio);
		verify(avioRepo, times(1)).findByAdmin(user);
        verifyNoMoreInteractions(avioRepo);
	}
	
	@Test
	public void testAddDestinationToAvio() {
		user.setUsername(UserConstants.NEW_USERNAME);
		user.setCity(UserConstants.NEW_CITY);
		user.setPhoneNumber(UserConstants.NEW_PHONE);
		user.setName(UserConstants.NEW_FIRST_NAME);
		user.setLastname(UserConstants.NEW_LAST_NAME);
		user.setEmail(UserConstants.NEW_EMAIL);
		user.setPassword(passwordEncoder.encode(UserConstants.NEW_PASSWORD));
		user.setEnabled(true);
		user.setPasswordChanged(false);
		user.setRole(Role.User);
		avio.setAddress(AvioConstants.ADDRESS);
		avio.setAdmin(user);
		avio.setAverageRate(AvioConstants.AVGRATE);
		avio.setId(AvioConstants.ID);
		avio.setName(AvioConstants.NAME);
		avio.setPromo(AvioConstants.PROMO);
		avio.setDestinations(new ArrayList<Destination>());
		AvioCompany modified = new AvioCompany();
		modified.setAddress(AvioConstants.ADDRESS);
		modified.setAdmin(user);
		modified.setAverageRate(AvioConstants.AVGRATE);
		modified.setId(AvioConstants.ID);
		modified.setName(AvioConstants.NAME);
		modified.setPromo(AvioConstants.PROMO);
		List<Destination> dests = new ArrayList<Destination>();
		dest.setName(DestinationConstants.NAME);
		dest.setDeleted(false);
		dest.setId(1L);
		dests.add(dest);
		modified.setDestinations(dests);
		when(avioRepo.findByAdmin(user)).thenReturn(avio);
		when(avioRepo.save(avio)).thenReturn(modified);
		
		AvioCompany res = avioService.addDestinationToAvio(DestinationConstants.NAME, user);
		
		assertEquals(modified, res);
		
		verify(avioRepo, times(1)).save(avio);
		verify(avioRepo, times(1)).findByAdmin(user);
		
//		when(flightResRepo.
//				findAvioCompanyAndDestination(avio, dest)).
//					thenReturn(new ArrayList<FlightReservation>());
//		verify(flightResRepo, times(1)).
//				findAvioCompanyAndDestination(avio, dest);
        verifyNoMoreInteractions(avioRepo);
	}
	
	@Test
	public void testRemoveDestinationFromAvio() {
		user.setUsername(UserConstants.NEW_USERNAME);
		user.setCity(UserConstants.NEW_CITY);
		user.setPhoneNumber(UserConstants.NEW_PHONE);
		user.setName(UserConstants.NEW_FIRST_NAME);
		user.setLastname(UserConstants.NEW_LAST_NAME);
		user.setEmail(UserConstants.NEW_EMAIL);
		user.setPassword(passwordEncoder.encode(UserConstants.NEW_PASSWORD));
		user.setEnabled(true);
		user.setPasswordChanged(false);
		user.setRole(Role.User);
		avio.setAddress(AvioConstants.ADDRESS);
		avio.setAdmin(user);
		avio.setAverageRate(AvioConstants.AVGRATE);
		avio.setId(AvioConstants.ID);
		avio.setName(AvioConstants.NAME);
		avio.setPromo(AvioConstants.PROMO);
		avio.setDestinations(new ArrayList<Destination>());
		AvioCompany modified = new AvioCompany();
		modified.setAddress(AvioConstants.ADDRESS);
		modified.setAdmin(user);
		modified.setAverageRate(AvioConstants.AVGRATE);
		modified.setId(AvioConstants.ID);
		modified.setName(AvioConstants.NAME);
		modified.setPromo(AvioConstants.PROMO);
		List<Destination> dests = new ArrayList<Destination>();
		dest.setName(DestinationConstants.NAME);
		dest.setDeleted(false);
		dest.setId(1L);
		dests.add(dest);
		modified.setDestinations(dests);
		when(avioRepo.findByAdmin(user)).thenReturn(modified);
		when(avioRepo.save(avio)).thenReturn(avio);
		when(destRepo.findById(1L)).thenReturn(Optional.of(dest));
		when(flightResRepo.
				findAvioCompanyAndDestination(avio, dest)).
					thenReturn(new ArrayList<FlightReservation>());
		
		AvioCompany res = avioService.removeDestinationFromAvio(1L, user);
		
		assertEquals(dests, res.getDestinations());
		
		verify(avioRepo, times(1)).save(res);
		verify(avioRepo, times(1)).findByAdmin(user);
        verifyNoMoreInteractions(avioRepo);
        verify(destRepo, times(1)).findById(1L);
        verifyNoMoreInteractions(destRepo);
        verify(flightResRepo, times(1)).
		findAvioCompanyAndDestination(res, dest);
        verifyNoMoreInteractions(flightResRepo);
	}
	
}

package edu.ftn.isa.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import edu.ftn.isa.constants.AvioConstants;
import edu.ftn.isa.constants.UserConstants;
import edu.ftn.isa.dto.FlightDTO;
import edu.ftn.isa.dto.SeatDTO;
import edu.ftn.isa.model.AvioCompany;
import edu.ftn.isa.model.Destination;
import edu.ftn.isa.model.Flight;
import edu.ftn.isa.model.FlightClass;
import edu.ftn.isa.model.FlightSeat;
import edu.ftn.isa.model.Role;
import edu.ftn.isa.model.User;
import edu.ftn.isa.repositories.AvioRepository;
import edu.ftn.isa.repositories.DestinationRepository;
import edu.ftn.isa.repositories.FlightRepository;
import edu.ftn.isa.repositories.FlightSeatRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestFlightService {

	
	@Mock
	private FlightRepository flightRepo;
	
	@Mock
	private AvioRepository avioRepo;
	
	@Mock
	private DestinationRepository destRepo;
	
	@Mock
	private FlightSeatRepository flightSeatRepo;
	
	@InjectMocks
	private FlightService flightService;
	
	@Test
    @Transactional
    @Rollback(true) //it can be omitted because it is true by default
	public void testAddFlight() throws ParseException {
		Flight flight = new Flight();
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
		AvioCompany avio = new AvioCompany();
		avio.setAddress(AvioConstants.ADDRESS);
		avio.setAdmin(user);
		avio.setAverageRate(AvioConstants.AVGRATE);
		avio.setId(AvioConstants.ID);
		avio.setName(AvioConstants.NAME);
		avio.setPromo(AvioConstants.PROMO);
		flight.setAvioCompany(avio);
		flight.setBaggageOver20Price(20f);
		
		flight.setBussinessClassPrice(250D);
		flight.setEconomicClassPrice(200D);
		flight.setConfigurationType("jumbojet");
		flight.setDiscount(10f);
		flight.setNumOfRows(10);
		Destination dest = new Destination();
		dest.setName("London, England");
		dest.setDeleted(false);
		dest.setId(1L);
		Destination dest1 = new Destination();
		dest1.setName("Beograd, Srbija");
		dest1.setDeleted(false);
		dest1.setId(2L);
		flight.setToDest(dest1);
		flight.setFrom(dest);
		Date takeoff = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2019-09-29 18:00");
		Date landing = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2019-09-29 19:00");
		flight.setTakeoff(takeoff);
		flight.setLanding(landing);
		flight.setVersion(0);
		
		when(flightRepo.save(flight)).thenReturn(flight);
		
		Flight f = flightService.saveFlight(flight);
		
		assertEquals(flight, f);
		
		verify(flightRepo, times(1)).save(flight);
		
	}
	
	@Test
	public void testCreateFlight() throws ParseException {
		FlightDTO flightData = new FlightDTO();
		
		flightData.setBusinessPrice(250D);
		flightData.setFrom("Sevilla, Spain");
		flightData.setTo("Bilbao, Spain");
		flightData.setDepart("2019-09-29 10:10");
		flightData.setLand("2019-09-29 11:00");
		flightData.setPriceForBaggageOver14kg(50);
		flightData.setEconomicPrice(200D);
		flightData.setConfigType("smalljet");
		flightData.setNumOfRows(10);
		flightData.setDiscount(10);
		
		List<SeatDTO> seats = new ArrayList<SeatDTO>();
		for(int i=0; i<10; ++i) {
			for(int j=0; j<7; ++j) {
				SeatDTO seatDto = new SeatDTO();
				seatDto.setColNum(1);
				seatDto.setFastRes(false);
				seatDto.setRowNum(1);
				seatDto.setFlightClass("business");
				seats.add(seatDto);
			}
		}
		flightData.setSeats(seats);
		
		Flight flight = new Flight();
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
		AvioCompany avio = new AvioCompany();
		avio.setAddress(AvioConstants.ADDRESS);
		avio.setAdmin(user);
		avio.setAverageRate(AvioConstants.AVGRATE);
		avio.setId(AvioConstants.ID);
		avio.setName(AvioConstants.NAME);
		avio.setPromo(AvioConstants.PROMO);
		flight.setAvioCompany(avio);
		flight.setBaggageOver20Price(50);
		flight.setBussinessClassPrice(250D);
		flight.setEconomicClassPrice(200D);
		flight.setConfigurationType("smalljet");
		flight.setDiscount(10f);
		flight.setNumOfRows(10);
		Destination dest = new Destination();
		dest.setName("Sevilla, Spain");
		dest.setDeleted(false);
		dest.setId(1L);
		Destination dest1 = new Destination();
		dest1.setName("Bilbao, Spain");
		dest1.setDeleted(false);
		dest1.setId(2L);
		flight.setToDest(dest1);
		flight.setFrom(dest);
		Date takeoff = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2019-09-29 10:10");
		Date landing = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2019-09-29 11:00");
		flight.setTakeoff(takeoff);
		flight.setLanding(landing);
//		flight.setVersion(0);
		
		FlightSeat fs = new FlightSeat();
		fs.setAvailable(true);
		fs.setFastReservation(false);
		fs.setFlight(flight);
		fs.setColNo(1);
		fs.setRowNo(1);
		fs.setSeatNumber(1);
//		fs.set
		fs.setFlightClass(FlightClass.Business);
		
		when(avioRepo.findById(avio.getId())).thenReturn(Optional.of(avio));
		when(destRepo.findByNameAndDeleted(dest1.getName(), false)).thenReturn(dest1);
		when(destRepo.findByNameAndDeleted(dest.getName(), false)).thenReturn(dest);
		when(flightRepo.save(flight)).thenReturn(flight);
		when(flightSeatRepo.save(fs)).thenReturn(fs);
		
		Flight f = flightService.createFlight(user, flightData);
		
		flight.setAvioCompany(null);
		assertEquals(flight, f);
		
		
//		verify(avioRepo, times(1)).findById(avio.getId());
//		verify(destRepo, times(1)).findByNameAndDeleted(dest1.getName(), false);
//		verify(destRepo, times(1)).findByNameAndDeleted(dest.getName(), false);
//		verify(flightRepo, times(1)).save(flight);
//		verify(flightSeatRepo, times(70)).save(fs);
		
		
		
	}

}

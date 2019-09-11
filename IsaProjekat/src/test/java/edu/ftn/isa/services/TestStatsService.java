package edu.ftn.isa.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;

import edu.ftn.isa.JsonMapper;
import edu.ftn.isa.dto.AvioStatisticsDTO;
import edu.ftn.isa.dto.FlightForStatsDTO;
import edu.ftn.isa.model.AvioCompany;
import edu.ftn.isa.model.Flight;
import edu.ftn.isa.payload.LoginPayload;
import edu.ftn.isa.repositories.AvioRepository;
import edu.ftn.isa.repositories.FlightRepository;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@Transactional
public class TestStatsService {

	private MockMvc mockMvc;

	@Autowired
	WebApplicationContext context;
	
	@Autowired
	private StatsService statsService;
	
	@Autowired
	private AvioRepository avioRepo;
	
	@Autowired
	private FlightRepository flightRepo;
	
	private JsonMapper mapper;
	
	private AvioCompany avio;
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		mapper = new JsonMapper();
		avio = avioRepo.findById(1L).get();
	}
	
	@Transactional
	@Rollback(true)
	@Test
	public void testLogin() throws JsonProcessingException, Exception {
		AvioStatisticsDTO statsRes = new AvioStatisticsDTO();
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm");
		statsRes.setAvgAvioRate(3);
		statsRes.setDailySoldTickets(0L);
		statsRes.setMonthlySoldTickets(28L);
		statsRes.setWeeklySoldTickets(28L);
		List<Flight> flightList = flightRepo.findByAvioCompany(avio);
		List<FlightForStatsDTO> flights = new ArrayList<FlightForStatsDTO>();
		for(int i=0; i<flightList.size(); ++i) {
			FlightForStatsDTO flight = new FlightForStatsDTO();
			flight.setAvgRate(flightRepo.getAverageRatingForFlight(flightList.get(i)));
			flight.setFrom(flightList.get(i).getFrom().getName());
			flight.setTo(flightList.get(i).getToDest().getName());
			flight.setDepart(formatter.format((flightList.get(i).getTakeoff())));
			flight.setLand((formatter.format((flightList.get(i).getLanding()))));
			flights.add(flight);
		}
		statsRes.setFlights(flights);
		
		avio = avioRepo.findById(1L).get();
		AvioStatisticsDTO statsDto = statsService.getAvioStats(avio);
		statsDto.getAvgAvioRate();
		
		assertTrue(statsDto.equals(statsRes));
	}
	
	
	
}

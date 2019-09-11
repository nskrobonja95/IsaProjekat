package edu.ftn.isa.controllers;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.MediaType;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
import edu.ftn.isa.dto.FlightRatingDTO;
import edu.ftn.isa.model.AvioCompany;
import edu.ftn.isa.repositories.AvioRepository;
import edu.ftn.isa.repositories.FlightRepository;
import edu.ftn.isa.repositories.HotelReservationRepository;
import edu.ftn.isa.services.StatsService;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@Transactional
public class TestCancelResAPI {

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
	
	@Autowired
	private HotelReservationRepository hotelResRepo;
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		mapper = new JsonMapper();
	}
	
	@Transactional
	@Rollback(true)
	@Test
	public void testCancelAcc() throws JsonProcessingException, Exception {
		Integer rate = 2;
		MvcResult result = 
				mockMvc.perform(
						MockMvcRequestBuilders.get("/user/cancelAccomodation/1")
							.contentType(MediaType.APPLICATION_JSON)).andReturn();
		
		assertEquals(200, result.getResponse().getStatus());
	}
	
	@Transactional
	@Rollback(true)
	@Test
	public void testCancelFlight() throws JsonProcessingException, Exception {
		MvcResult result = 
				mockMvc.perform(
						MockMvcRequestBuilders.get("/user/cancelFlight/1")
							.contentType(MediaType.APPLICATION_JSON)).andReturn();
		
		assertEquals(200, result.getResponse().getStatus());
	}
	
}
